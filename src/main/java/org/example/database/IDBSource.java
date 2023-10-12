package org.example.database;

import com.zaxxer.hikari.HikariConfig;
import java.sql.Connection;
import java.sql.SQLException;
import org.example.database.property.DBProperty;
import org.example.database.property.DBPropertyType;

public interface IDBSource {

    Connection connect() throws SQLException;

    void disconnect();

    String getName();

    default HikariConfig getConfig(final DBProperty property) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(property.getDataSourceClassName());
        config.setJdbcUrl(this.makeJdbcUrl(property));
        config.setUsername(property.getDbId());
        config.setPassword(property.getDbPw());
        config.setAutoCommit(property.isAutoCommit());
        config.setConnectionTimeout(property.getConnectionTimeout());
        config.setIdleTimeout(property.getIdleTimeout());
        config.setMaxLifetime(property.getMaxLifetime());
        config.setConnectionTestQuery(property.getConnectionTestQuery());
        config.setMaximumPoolSize(property.getMaximumPoolSize());
        config.setLeakDetectionThreshold(property.getLeakDetectionThreshold());

        return config;
    }

    default String makeJdbcUrl(final DBProperty property) {
        final DBPropertyType dbPropertyType = property.getDbPropertyType();
        final String url = dbPropertyType.getConnUrl();
        final String ip = property.getDbIp();
        final String port = String.valueOf(property.getDbPort());
        final String dbName = property.getDbName();

        return url + ip + ":" + port + "/" + dbName;
    }
}
