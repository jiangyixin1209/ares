package top.jiangyixin.ares.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Properties 工具类
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/18
 */
public class PropertiesUtils {
    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    public static final String DEFAULT_CONFIG = "ares.properties";

    /**
     * 加载 Properties 文件
     * @param propertyFilename  Properties 文件名
     * @return                  Properties 对象
     */
    public static Properties loadProperties(String propertyFilename) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(propertyFilename);
            if (url != null) {
                inputStream = new FileInputStream(url.getPath());
                properties.load(inputStream);
            }
        } catch (IOException e) {
            logger.error("load {} error", propertyFilename);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close {} error", propertyFilename);
                }
            }
        }
        return properties;
    }

    /**
     * 加载 properties 字符串配置
     * @param properties        properties
     * @param key               key
     * @return                  string value
     */
    public static String getString(Properties properties, String key) {
        return properties.getProperty(key);
    }

    /**
     * 加载 properties 整数配置
     * @param properties        properties
     * @param key               key
     * @return                  int value
     */
    public static int getInt(Properties properties, String key) {
        return Integer.parseInt(getString(properties, key));
    }

    /**
     * 加载 properties 整数配置
     * @param properties        properties
     * @param key               key
     * @return                  bool value
     */
    public static boolean getBoolean(Properties properties, String key) {
        return Boolean.parseBoolean(getString(properties, key));
    }
}
