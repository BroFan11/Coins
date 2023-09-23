package me.brofan11.erme.api;

import me.brofan11.erme.Erme;
import me.brofan11.erme.manager.cache.CacheUser;

import java.util.UUID;

public class CoinsAPI {
    private static Erme instance;

    private CoinsAPI() {
        // EMPTY
    }

    public static Erme getInstance() {
        return instance;
    }

    public static void setInstance(Erme instance) {
        CoinsAPI.instance = instance;
    }

    public static long getCoin(UUID uuid) {
        return instance.getCacheManager().getUser(uuid).map(CacheUser::getValue).orElseGet(() -> instance.getDataManager().getCoin(uuid));
    }

    public static void addCoin(UUID uuid, long coin) {
        instance.getDataManager().addCoin(uuid, coin);
    }

    public static boolean takeCoin(UUID uuid, long coin) {
        return instance.getDataManager().takeCoin(uuid, coin);
    }

    public static void setCoin(UUID uuid, long coin) {
        instance.getDataManager().setCoin(uuid, coin);
    }
}
