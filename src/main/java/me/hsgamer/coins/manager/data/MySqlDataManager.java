package me.hsgamer.coins.manager.data;

import me.hsgamer.coins.config.MainConfig;
import me.hsgamer.hscore.database.Setting;
import me.hsgamer.hscore.database.client.sql.SqlClient;
import me.hsgamer.hscore.database.client.sql.java.JavaSqlClient;
import me.hsgamer.hscore.database.driver.MySqlDriver;

public class MySqlDataManager extends SqlDataManager {
    @Override
    protected SqlClient<?> createClient() {
        try {
            return new JavaSqlClient(
                    new Setting()
                            .setHost(MainConfig.MYSQL_HOST.getValue())
                            .setPort(MainConfig.MYSQL_PORT.getValue())
                            .setDatabaseName(MainConfig.MYSQL_DATABASE.getValue())
                            .setUsername(MainConfig.MYSQL_USER.getValue())
                            .setPassword(MainConfig.MYSQL_PASSWORD.getValue()),
                    new MySqlDriver()
            );
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("The driver class is not found", e);
        }
    }
}
