package me.hsgamer.coins.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import org.bukkit.plugin.Plugin;

public class MessageConfig extends PathableConfig {
    public MessageConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "messages.yml"));
    }
}
