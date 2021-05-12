package me.hsgamer.coins.api;

import me.hsgamer.coins.Coins;
import me.hsgamer.coins.manager.cache.CacheUser;

import java.util.UUID;

public class CoinsAPI {
    private static Coins instance;

    private CoinsAPI() {
        // EMPTY
    }

    public static Coins getInstance() {
        return instance;
    }

    public static void setInstance(Coins instance) {
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
