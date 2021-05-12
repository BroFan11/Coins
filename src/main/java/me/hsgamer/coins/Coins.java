package me.hsgamer.coins;

import me.hsgamer.coins.api.CoinsAPI;
import me.hsgamer.coins.command.AddCoinCommand;
import me.hsgamer.coins.command.GetCoinCommand;
import me.hsgamer.coins.config.MainConfig;
import me.hsgamer.coins.config.MessageConfig;
import me.hsgamer.coins.listener.PlayerListener;
import me.hsgamer.coins.manager.cache.CacheManager;
import me.hsgamer.coins.manager.data.DataManager;
import me.hsgamer.coins.manager.data.MySqlDataManager;
import me.hsgamer.coins.manager.data.SqliteDataManager;
import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;

public final class Coins extends BasePlugin {
    private final MainConfig mainConfig = new MainConfig(this);
    private final MessageConfig messageConfig = new MessageConfig(this);
    private final CacheManager cacheManager = new CacheManager();
    private DataManager dataManager;

    @Override
    public void preLoad() {
        CoinsAPI.setInstance(this);
    }

    @Override
    public void load() {
        MessageUtils.setPrefix(MessageConfig.PREFIX::getValue);
        mainConfig.setup();
        messageConfig.setup();
    }

    @Override
    public void enable() {
        loadStorage();

        registerListener(new PlayerListener(this));

        registerCommand(new GetCoinCommand());
        registerCommand(new AddCoinCommand());
    }

    @Override
    public void disable() {
        cacheManager.clearAll();
        dataManager.disable();
    }

    private void loadStorage() {
        String type = MainConfig.STORAGE_TYPE.getValue();
        if (type.equalsIgnoreCase("sqlite")) {
            dataManager = new SqliteDataManager();
        } else if (type.equalsIgnoreCase("mysql")) {
            dataManager = new MySqlDataManager();
        } else {
            throw new IllegalStateException(type + " is not a valid storage type. Only 'SQLITE' or 'MYSQL'");
        }

        dataManager.setup();
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }
}
