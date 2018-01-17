package com.zhuguang.jack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheServiceImpl implements CacheService {
    
    @Autowired
    private CacheManager cm;
    
    public <V> V cacheResult(String key, String cacheName) {
        ValueWrapper valueWrapper = cm.getCache(cacheName).get(key);
        return valueWrapper == null ? null : (V)valueWrapper.get();
    }
    
    public void cacheRemove(String key, String cacheName) {
        cm.getCache(cacheName).evict(key);
    }
    
    public <V> void cachePut(String key, V value, String cacheName) {
        cm.getCache(cacheName).put(key, value);
    }
    
}
