package com.zhuguang.jack.service;

public interface CacheService {
    
    public <V> V cacheResult(String key, String cacheName);
    
    public void cacheRemove(String key, String cacheName);
    
    public <V> void cachePut(String key, V value, String cacheName);
}
