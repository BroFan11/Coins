package me.hsgamer.coins.manager.data;

import java.util.UUID;

public interface DataManager {
    void setup();

    void disable();

    long getCoin(UUID uuid);

    void setCoin(UUID uuid, long coin);

    default void addCoin(UUID uuid, long coin) {
        long current = getCoin(uuid);
        setCoin(uuid, current + coin);
    }

    default boolean takeCoin(UUID uuid, long coin) {
        long current = getCoin(uuid);
        if (current < coin) {
            return false;
        }
        setCoin(uuid, current + coin);
        return true;
    }
}
