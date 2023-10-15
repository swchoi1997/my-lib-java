package org.example.database.object;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.example.database.property.DBProperty;
import org.example.database.source.DBSourceLocal;
import org.example.database.source.DBSourceSsh;
import org.example.database.source.IDBSource;
import org.example.ssh.SshTunnel;

public class DBObject {
    private static final Logger LOG = Logger.getLogger(DBObject.class);

    private final IDBSource dataSource;

    public DBObject(final DBProperty property) {
        this(new DBSourceLocal(property));
    }

    public DBObject(final SshTunnel sshTunnel, final DBProperty property) {
        this(new DBSourceSsh(sshTunnel, property));
    }

    private DBObject(IDBSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        try {
            return this.dataSource.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        this.dataSource.disconnect();
    }

    public String getName() {
        if (this.dataSource == null) {
            throw new RuntimeException();
        }
        return this.dataSource.getName();
    }
}
