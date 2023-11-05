package org.example.database.source;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.example.database.property.DBProperty;
import org.example.ssh.SshTunnel;

public class DBSourceSsh implements IDBSource{

    private final DBProperty property;
    private final String name;
    private final HikariDataSource hikariDataSource;
    private final SshTunnel sshTunnel;

    public DBSourceSsh(SshTunnel sshTunnel, DBProperty property) {
        this.property = property;
        this.name = property.getDbIp() + ":" + property.getDbPort() + "/" + property.getDbName();
        this.sshTunnel = sshTunnel;
        this.sshTunnel.connect().portForwarding(this.property.getDbPort());
        this.hikariDataSource = new HikariDataSource(this.getConfig(property));
    }

    public Connection connect() throws SQLException {
        return this.hikariDataSource.getConnection();
    }

    public void disconnect() {
        this.sshTunnel.disconnect();
        this.hikariDataSource.close();
    }

    public String getName() {
        return name;
    }

    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    public SshTunnel getSshTunnel() {
        return sshTunnel;
    }

    public DBProperty getProperty() {
        return property;
    }
}
