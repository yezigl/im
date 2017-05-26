/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月26日
 */
@Service
public class CacheService {
    
    Map<String, Object> cache = new ConcurrentHashMap<>();

    public void set(String key, Object value) {
        cache.put(key, value);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) cache.get(key);
    }
}
