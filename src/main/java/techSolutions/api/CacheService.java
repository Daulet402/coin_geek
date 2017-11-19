package techSolutions.api;

public interface CacheService {

    Object getFromCache(String key);

    void clearCache();

    void removeFromCache(String key);

    void addToCache(String key, Object value);

    Object getCache();
}