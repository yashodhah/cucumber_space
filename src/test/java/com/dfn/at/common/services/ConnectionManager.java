package com.dfn.at.common.services;

import com.dfn.at.common.beans.DatabaseConfiguration;
import com.dfn.at.common.util.ConfigurationReader;
import com.dfn.at.core.constants.EnvironmentConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final ConnectionManager manager = new ConnectionManager();
    private static HikariDataSource hikariDataSource;

    public static ConnectionManager getConnectionManager() {
        return manager;
    }

    private ConnectionManager() {
    }

    public Connection getHikariDataSourceConnection() throws SQLException {
        return getHikariDataSource().getConnection();
    }

    public static HikariDataSource getHikariDataSource() throws SQLException {
        if (hikariDataSource != null) {
            return hikariDataSource;
        }

        synchronized (ConnectionManager.class) {
            try {
                HikariConfig config = new HikariConfig();
                DatabaseConfiguration databaseConfiguration = ConfigurationReader.getApplicationConfiguration().getDatabaseConfiguration();

                config.setJdbcUrl(databaseConfiguration.getUrl());
                config.setUsername(databaseConfiguration.getUser());
                config.setPassword(databaseConfiguration.getPassword());
                config.addDataSourceProperty("cachePrepStmts", "true");
                config.addDataSourceProperty("prepStmtCacheSize", "250");
                config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
                config.setMaximumPoolSize(EnvironmentConstants.MAX_POOL_SIZE);
                /*maximum number of milliseconds that a client will wait for a connection from the pool*/
                config.setConnectionTimeout(EnvironmentConstants.POOL_CONNECTION_TIMEOUT);
                /*This property controls the maximum lifetime of a connection in the pool. A value of 0 indicates no maximum lifetime (infinite lifetime)*/
                config.setMaxLifetime(EnvironmentConstants.POOL_CONNECTION_MAX_LIFETIME);
                /*maximum amount of time that a connection is allowed to sit idle in the pool*/
                config.setIdleTimeout(EnvironmentConstants.POOL_CONNECTION_IDLE_TIMEOUT);
                /*This property controls the minimum number of idle connections that HikariCP tries to maintain in the pool*/
                config.setMinimumIdle(EnvironmentConstants.MINIMUM_IDLE);

                if (hikariDataSource != null) {
                    return hikariDataSource;
                }

                hikariDataSource = new HikariDataSource(config);
                return hikariDataSource;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
