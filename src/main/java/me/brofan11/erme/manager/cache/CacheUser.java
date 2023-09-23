package me.brofan11.erme.manager.cache;

import me.brofan11.erme.config.MainConfig;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static me.brofan11.erme.api.CoinsAPI.getInstance;

public class CacheUser extends BukkitRunnable {
    private final UUID uuid;
    private long value = 0;

    public CacheUser(UUID uuid) {
        this.uuid = uuid;
        long update = Math.max(0, MainConfig.CACHE_UPDATE_TICKS.getValue());
        if (MainConfig.CACHE_UPDATE_ASYNC.getValue()) {
            runTaskTimerAsynchronously(getInstance(), 0, update);
        } else {
            runTaskTimer(getInstance(), 0, update);
        }
    }

    @Override
    public void run() {
        this.value = getInstance().getDataManager().getCoin(uuid);
    }

    public long getValue() {
        return value;
    }
}
