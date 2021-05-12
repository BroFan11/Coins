package me.hsgamer.coins.manager.data;

import me.hsgamer.hscore.database.client.sql.PreparedStatementContainer;
import me.hsgamer.hscore.database.client.sql.SqlClient;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public abstract class SqlDataManager implements DataManager {
    private Connection connection;

    protected abstract SqlClient<?> createClient();

    @Override
    public void setup() {
        SqlClient<?> client = createClient();
        client.prepareStatementSafe(Query.CREATE.queryCommand).ifPresent(PreparedStatementContainer::updateSafe);
        try {
            connection = client.getConnection();
        } catch (SQLException exception) {
            throw new IllegalStateException("Cannot get connection", exception);
        }
    }

    @Override
    public void disable() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new IllegalStateException("Cannot close connection", exception);
        }
    }

    private PreparedStatementContainer prepareStatement(String query, Object... args) {
        PreparedStatementContainer container;
        try {
            container = PreparedStatementContainer.of(connection, query, args);
        } catch (SQLException exception) {
            throw new IllegalStateException("Cannot prepare statement", exception);
        }
        return container;
    }

    private boolean hasProfile(UUID uuid) {
        try (PreparedStatementContainer container = prepareStatement(Query.GET.queryCommand, uuid)) {
            return container.querySafe()
                    .map(resultSet -> {
                        try {
                            return resultSet.next();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                            return false;
                        }
                    }).orElse(false);
        } catch (SQLException exception) {
            throw new IllegalStateException("Cannot close statement", exception);
        }
    }

    @Override
    public long getCoin(UUID uuid) {
        try (PreparedStatementContainer container = prepareStatement(Query.GET.queryCommand, uuid)) {
            return container.querySafe()
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
        } catch (SQLException exception) {
            throw new IllegalStateException("Cannot close statement", exception);
        }
    }

    @Override
    public void setCoin(UUID uuid, long coin) {
        PreparedStatementContainer container;
        if (hasProfile(uuid)) {
            container = prepareStatement(Query.SET.queryCommand, coin, uuid.toString());
        } else {
            container = prepareStatement(Query.INSERT.queryCommand, uuid.toString(), coin);
        }
        container.updateSafe();
        try {
            container.close();
        } catch (SQLException exception) {
            throw new IllegalStateException("Cannot close statement", exception);
        }
    }

    public enum Query {
        CREATE("CREATE TABLE IF NOT EXISTS coins (uuid VARCHAR(100), coin INT(32), PRIMARY KEY (uuid))"),
        INSERT("INSERT INTO coins(uuid, coin) VALUES(?, ?)"),
        GET("SELECT * FROM coins WHERE uuid = ?"),
        SET("UPDATE coins SET coin = ? WHERE uuid = ?");

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
