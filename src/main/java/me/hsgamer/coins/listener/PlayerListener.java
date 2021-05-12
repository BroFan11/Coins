package me.hsgamer.coins.listener;

import me.hsgamer.coins.Coins;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final Coins instance;

    public PlayerListener(Coins instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        instance.getCacheManager().addUser(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        instance.getCacheManager().removeUser(event.getPlayer().getUniqueId());
    }
}
