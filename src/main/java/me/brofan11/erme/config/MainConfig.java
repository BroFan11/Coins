package me.brofan11.erme.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.BooleanConfigPath;
import me.hsgamer.hscore.config.path.LongConfigPath;
import me.hsgamer.hscore.config.path.StringConfigPath;
import org.bukkit.plugin.Plugin;

public class MainConfig extends PathableConfig {
    public static final StringConfigPath STORAGE_TYPE = new StringConfigPath("storage-type", "SQLITE");
    public static final StringConfigPath MYSQL_HOST = new StringConfigPath("mysql.host", "localhost");
    public static final StringConfigPath MYSQL_PORT = new StringConfigPath("mysql.port", "3306");
    public static final StringConfigPath MYSQL_DATABASE = new StringConfigPath("mysql.database", "coins");
    public static final StringConfigPath MYSQL_USER = new StringConfigPath("mysql.user", "admin");
    public static final StringConfigPath MYSQL_PASSWORD = new StringConfigPath("mysql.password", "");

    public static final BooleanConfigPath CACHE_UPDATE_ASYNC = new BooleanConfigPath("cache.update-async", true);
    public static final LongConfigPath CACHE_UPDATE_TICKS = new LongConfigPath("cache.update-ticks", 10L);

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
