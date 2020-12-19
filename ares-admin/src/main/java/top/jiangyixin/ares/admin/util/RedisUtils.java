package top.jiangyixin.ares.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import top.jiangyixin.ares.admin.exception.AresException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis 工具类
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/18
 */
public class RedisUtils {

    private final static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    /**
     * 默认过期时间（单位/秒），默认两小时
     */
    private static final int DEFAULT_EXPIRE_TIME = 60 * 60 * 2;
    private static ShardedJedisPool shardedJedisPool;
    private final static ReentrantLock INIT_LOCK = new ReentrantLock();

    public static ShardedJedis getInstance() {
        if (shardedJedisPool == null) {
            boolean initSuccess = initSharedJedis();
            if (!initSuccess) {
                throw new AresException("Ares RedisUtils ShardedJedisPool init fail, ShardedJedisPool is null");
            }
        }

        if (shardedJedisPool == null) {
            throw new AresException("Ares RedisUtils ShardedJedisPool init fail, ShardedJedisPool is null");
        }
        return shardedJedisPool.getResource();
    }

    public static boolean initSharedJedis() {
        try {
            if (INIT_LOCK.tryLock(2, TimeUnit.SECONDS)) {
                if (shardedJedisPool != null) {
                    return true;
                }
                try {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    // 最大连接数，默认8个
                    jedisPoolConfig.setMaxTotal(200);
                    // 最大空闲连接数，默认8个
                    jedisPoolConfig.setMaxIdle(50);
                    // 设置最小空闲连接数
                    jedisPoolConfig.setMinIdle(8);
                    // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted)
                    // 如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
                    jedisPoolConfig.setMaxWaitMillis(100000);
                    // 在获取连接的时候检查有效性, 默认false
                    jedisPoolConfig.setTestOnBorrow(true);
                    // 调用returnObject方法时，是否进行有效检查
                    jedisPoolConfig.setTestOnReturn(true);
                    // // Idle时进行连接扫描
                    jedisPoolConfig.setTestWhileIdle(true);
                    // 表示idle object evitor两次扫描之间要sleep的毫秒数
                    jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
                    // 表示idle object evitor每次扫描的最多的对象数
                    jedisPoolConfig.setNumTestsPerEvictionRun(10);
                    // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
                    // 这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
                    jedisPoolConfig.setMinEvictableIdleTimeMillis(60000);

                    List<JedisShardInfo> jedisShardInfoList = new LinkedList<JedisShardInfo>();
                    Properties properties = PropertiesUtils.loadProperties(PropertiesUtils.DEFAULT_CONFIG);
                    String addresses = PropertiesUtils.getString(properties, "jedis.address");
                    for (String address : addresses.split(",")) {
                        String[] addressInfo = address.split(":");
                        String host = addressInfo[0];
                        int port = Integer.parseInt(addressInfo[1]);
                        JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, 10000);
                        jedisShardInfoList.add(jedisShardInfo);
                    }
                    shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfoList);
                    logger.info("Ares RedisUtils ShardedJedisPool init success!");
                    return true;
                } finally {
                    INIT_LOCK.unlock();
                }
            }
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
        return false;
    }

    /**
     * 将对象序列化成byte[]
     * @param obj           对象
     * @return              byte[]
     */
    public static byte[] serialize(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);){
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 将 byte[] 反序列化成对象
     * @param bytes         byte[]
     * @return              对象
     */
    public static Object deserialization(byte[] bytes) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
            return objectInputStream.readObject();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 设置 String 类型数据
     * @param key           key
     * @param value         value
     * @param seconds       过期时间，单位/秒
     * @return
     */
    public static String setStringValue(String key, String value, int seconds) {
        String result = null;
        try(ShardedJedis shardedJedis = getInstance()) {
            result = shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return result;
    }

    /**
     * 设置 String 类型数据（默认存活2H）
     * @param key           key
     * @param value         value
     * @return
     */
    public static String setStringValue(String key, String value) {
        return setStringValue(key, value, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 设置 Object 类型数据
     * @param key           key
     * @param obj           obj
     * @param seconds       过期时间(单位/秒)
     * @return
     */
    public static String setObjectValue(String key, Object obj, int seconds) {
        String result = null;
        try (ShardedJedis shardedJedis = getInstance()){
            result = shardedJedis.setex(key.getBytes(), seconds, serialize(obj));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 获取String类型数据
     * @param key       key
     * @return          String value
     */
    public static String getStringValue(String key) {
        String value = null;
        try (ShardedJedis shardedJedis = getInstance()){
            value = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return value;
    }

    /**
     * 获取 Object 类型数据
     * @param key           key
     * @return              Object value
     */
    public static Object getObjectValue(String key) {
        Object object = null;
        try (ShardedJedis shardedJedis = getInstance()){
            byte[] bytes = shardedJedis.get(key.getBytes());
            if (bytes != null && bytes.length > 0) {
                object = deserialization(bytes);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return object;
    }

    /**
     * del 命令
     * @param key       key
     * @return
     */
    public static Long del(String key) {
        Long result = null;
        try (ShardedJedis shardedJedis = getInstance()){
            result = shardedJedis.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * incrBy 命令
     *      value 加 i
     * @param key               key
     * @param i                 步长
     * @return                  自增后的值
     */
    public static Long incrBy(String key, int i) {
        Long result = null;
        try (ShardedJedis shardedJedis = getInstance()){
            result = shardedJedis.incrBy(key, i);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * exists 命令
     * @param key               key
     * @return
     */
    public static Boolean exists(String key) {
        Boolean result = null;
        try (ShardedJedis shardedJedis = getInstance()){
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * expire 命令
     * @param key               key
     * @param seconds           存活时间，单位/秒
     * @return
     */
    public static Long expire(String key, int seconds) {
        Long result = null;
        try (ShardedJedis shardedJedis = getInstance()){
            result = shardedJedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * expireAt 命令
     * @param key               key
     * @param unixTime          存活截止时间戳
     * @return
     */
    public static Long expireAt(String key, long unixTime) {
        Long result = null;
        try (ShardedJedis shardedJedis = getInstance()){
            result = shardedJedis.expireAt(key, unixTime);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}
