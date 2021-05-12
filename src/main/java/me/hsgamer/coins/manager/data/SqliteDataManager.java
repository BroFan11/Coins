package me.hsgamer.coins.manager.data;

import me.hsgamer.coins.api.CoinsAPI;
import me.hsgamer.hscore.database.Setting;
import me.hsgamer.hscore.database.client.sql.SqlClient;
import me.hsgamer.hscore.database.client.sql.java.JavaSqlClient;
import me.hsgamer.hscore.database.driver.SqliteDriver;

public class SqliteDataManager extends SqlDataManager {
    @Override
    protected SqlClient<?> createClient() {
        try {
            return new JavaSqlClient(new Setting().setDatabaseName("data"), new SqliteDriver(CoinsAPI.getInstance().getDataFolder()));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("The driver class is not found", e);
        }
    }
}
