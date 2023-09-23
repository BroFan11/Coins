package me.brofan11.erme.listener;

import me.brofan11.erme.Erme;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final Erme instance;

    public PlayerListener(Erme instance) {
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
