package me.hsgamer.coins.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.StringConfigPath;
import org.bukkit.plugin.Plugin;

public class MessageConfig extends PathableConfig {
    public static final StringConfigPath PREFIX = new StringConfigPath("prefix", "&f[&eCoins&f] ");
    public static final StringConfigPath PLAYER_ONLY = new StringConfigPath("player-only", "&cThis is for player only");
    public static final StringConfigPath GET_COIN_MESSAGE = new StringConfigPath("get-coin-message", "&aCoin: &f{coin}");

    public MessageConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "messages.yml"));
    }
}
