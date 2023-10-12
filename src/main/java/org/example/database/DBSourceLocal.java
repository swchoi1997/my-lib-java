package org.example.database;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.example.database.property.DBProperty;

public class DBSourceLocal implements IDBSource{

    private final DBProperty property;
    private final String name;
    private final HikariDataSource hikariDataSource;

    public DBSourceLocal(final DBProperty property) {
        this.property = property;
        this.name = property.getDbIp() + ":" + property.getDbPort() + "/" + property.getDbName();
        this.hikariDataSource = new HikariDataSource(this.getConfig(property));
    }

    public Connection connect() throws SQLException {
        return this.hikariDataSource.getConnection();
    }

    public void disconnect() {
        this.hikariDataSource.close();
    }

    public String getName() {
        return name;
    }

    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    public DBProperty getProperty() {
        return property;
    }
}
