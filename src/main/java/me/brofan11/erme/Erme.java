package me.brofan11.erme;

import me.brofan11.erme.listener.PlayerListener;
import me.brofan11.erme.manager.cache.CacheManager;
import me.brofan11.erme.manager.data.SqliteDataManager;
import me.brofan11.erme.api.CoinsAPI;
import me.brofan11.erme.command.AddCoinCommand;
import me.brofan11.erme.command.GetCoinCommand;
import me.brofan11.erme.command.SetCoinCommand;
import me.brofan11.erme.command.TakeCoinCommand;
import me.brofan11.erme.config.MainConfig;
import me.brofan11.erme.config.MessageConfig;
import me.brofan11.erme.manager.data.DataManager;
import me.brofan11.erme.manager.data.MySqlDataManager;
import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;

public final class Erme extends BasePlugin {
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
        registerCommand(new SetCoinCommand());
        registerCommand(new TakeCoinCommand());
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
