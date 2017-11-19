package techSolutions.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import techSolutions.dto.CacheDTO;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component("objectLTBasedCache")
@Scope("singleton")
public class ObjectLifeTimeBasedCacheServiceImpl extends TimeBasedCacheServiceImpl {

    private Map<String, CacheDTO> cache;

    @Value("${cache.update.duration:600}")
    private Integer cacheUpdateDuration;

    @PostConstruct
    @Override
    public void init() {
        cache = new HashMap<>();
    }

    @Override
    public Object getFromCache(String key) {
        CacheDTO cacheDTO = cache.get(key);
        if (cacheDTO == null) {
            return null;
        }
        long lastUpdatedDuration = cacheDTO.getValueUpdatedTime().until(
                LocalDateTime.now(),
                ChronoUnit.SECONDS);
        if (lastUpdatedDuration >= cacheUpdateDuration) {
            this.removeFromCache(key);
            return null;
        }
        return cacheDTO.getValue();
    }

    @Override
    public void addToCache(String key, Object value) {
        cache.put(key, new CacheDTO(value, LocalDateTime.now()));
    }

    @Override
    public void removeFromCache(String key) {
        cache.remove(key);
    }

    @Override
    public Object getCache() {
        return cache;
    }
}