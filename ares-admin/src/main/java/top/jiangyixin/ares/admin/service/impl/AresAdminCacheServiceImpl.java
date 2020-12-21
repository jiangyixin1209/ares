package top.jiangyixin.ares.admin.service.impl;

import org.springframework.stereotype.Service;
import top.jiangyixin.ares.admin.util.RedisUtil;
import top.jiangyixin.ares.core.AresCacheKey;
import top.jiangyixin.ares.core.AresCacheService;

/**
 * Admin CacheService Service 实现
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/19
 */
@Service
public class AresAdminCacheServiceImpl implements AresCacheService {

    /**
     * 设置缓存
     * @param aresCacheKey  cache key
     * @param value         cache value
     * @return              true/false
     */
    @Override
    public boolean set(AresCacheKey aresCacheKey, Object value) {
        return false;
    }

    /**
     * 查询缓存
     * @param aresCacheKey  cache key
     * @return              object value
     */
    @Override
    public Object get(AresCacheKey aresCacheKey) {
        return RedisUtil.getObjectValue(aresCacheKey.getRealKey());
    }

    /**
     * 删除缓存
     * @param aresCacheKey  cache key
     * @return              true/false
     */
    @Override
    public boolean delete(AresCacheKey aresCacheKey) {
        Long result = RedisUtil.del(aresCacheKey.getRealKey());
        return result != null && result > 0;
    }
}
