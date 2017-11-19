package techSolutions.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import techSolutions.api.CacheService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
@PropertySource("classpath:application.properties")
public class TimeBasedCacheServiceImpl implements CacheService {

    private Map<String, Object> cache;
    private LocalDateTime cacheUpdatedTime;

    @Value("${cache.update.duration:600}")
    private Integer cacheUpdateDuration;

    @PostConstruct
    public void init() {
        cache = new HashMap<>();
        cacheUpdatedTime = LocalDateTime.now();
    }

    @Override
    public Object getFromCache(String key) {
        clearCacheIfTimePassed();
        return cache.get(key);
    }

    @Override
    public void clearCache() {
        cache.clear();
    }

    @Override
    public void addToCache(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void removeFromCache(String key) {
        cache.remove(key);
    }

    public void clearCacheIfTimePassed() {
        long lastUpdatedDuration = cacheUpdatedTime.until(
                LocalDateTime.now(),
                ChronoUnit.SECONDS);
        if (lastUpdatedDuration >= cacheUpdateDuration) {
            this.clearCache();
            cacheUpdatedTime = LocalDateTime.now();
        }
    }

    @Override
    public Object getCache() {
        return cache;
    }
}