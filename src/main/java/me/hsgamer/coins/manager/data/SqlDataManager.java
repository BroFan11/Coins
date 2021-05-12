package me.hsgamer.coins.manager.data;

import me.hsgamer.hscore.database.client.sql.PreparedStatementContainer;
import me.hsgamer.hscore.database.client.sql.SqlClient;
import org.intellij.lang.annotations.Language;

import java.sql.SQLException;
import java.util.UUID;

public abstract class SqlDataManager implements DataManager {
    private SqlClient<?> client;

    protected abstract SqlClient<?> createClient();

    @Override
    public void setup() {
        this.client = createClient();
        client.prepareStatementSafe(Query.CREATE.queryCommand).ifPresent(PreparedStatementContainer::updateSafe);
    }

    @Override
    public void disable() {
        // EMPTY
    }

    @Override
    public long getCoin(UUID uuid) {
        return client.prepareStatementSafe(Query.GET.queryCommand, uuid.toString()).flatMap(PreparedStatementContainer::querySafe)
                .map(resultSet -> {
                    try {
                        if (resultSet.next()) {
                            return resultSet.getLong("coin");
                        } else {
                            return 0L;
                        }
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                    return -1L;
                }).orElse(0L);
    }

    @Override
    public void setCoin(UUID uuid, long coin) {
        client.prepareStatementSafe(Query.SET.queryCommand, uuid.toString(), coin, coin).ifPresent(PreparedStatementContainer::updateSafe);
    }

    public enum Query {
        CREATE("CREATE TABLE IF NOT EXISTS coins (uuid VARCHAR(100), coin INT(32), PRIMARY KEY (uuid))"),
        GET("SELECT * FROM coins WHERE uuid = ?"),
        SET("INSERT INTO coins (uuid, coin) VALUES (?,?) ON DUPLICATE KEY UPDATE coin = ?");

        @Language("SQL")
        private final String queryCommand;

        Query(@Language("SQL") String queryCommand) {
            this.queryCommand = queryCommand;
        }

        @Language("SQL")
        public String getQueryCommand() {
            return queryCommand;
        }
    }
}
