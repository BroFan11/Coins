package me.hsgamer.coins.manager.cache;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
    private final Map<UUID, CacheUser> cacheUserMap = new ConcurrentHashMap<>();

    public void addUser(UUID uuid) {
        cacheUserMap.put(uuid, new CacheUser(uuid));
    }

    public void removeUser(UUID uuid) {
        Optional.ofNullable(cacheUserMap.remove(uuid)).ifPresent(CacheUser::cancel);
    }

    public void clearAll() {
        cacheUserMap.values().forEach(CacheUser::cancel);
        cacheUserMap.clear();
    }

    public CacheUser getUser(UUID uuid) {
        return cacheUserMap.get(uuid);
    }
}
