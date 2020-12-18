package top.jiangyixin.ares.core;

/**
 * 公共缓存服务
 *      将该服务抽象成公共RPC服务
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:00
 */
public interface AresCacheService {
	
	/**
	 * 设置缓存
	 * @param aresCacheKey  cache key
	 * @param value         cache value
	 * @return              true/false
	 */
	boolean set(AresCacheKey aresCacheKey, Object value);
	
	/**
	 * 获取缓存
	 * @param aresCacheKey  cache key
	 * @return              cache object value
	 */
	Object get(AresCacheKey aresCacheKey);
	
	/**
	 * 删除缓存
	 * @param aresCacheKey  cache key
	 * @return              true/false
	 */
	boolean delete(AresCacheKey aresCacheKey);
}
