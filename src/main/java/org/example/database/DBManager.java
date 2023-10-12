package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.example.database.property.DBProperty;
import org.example.ssh.SshTunnel;

public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);
    private final ConcurrentHashMap<String, IDBSource> dataSources = new ConcurrentHashMap<>();

    private DBManager() {}

    public static synchronized DBManager getInstance() {
        return DBManager.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final DBManager INSTANCE = new DBManager();
    }

    public void append(final DBProperty property) {
        synchronized (this) {
            IDBSource idbSource = new DBSourceLocal(property);
            dataSources.put(idbSource.getName(), idbSource);
        }
    }

    public void append(final SshTunnel sshTunnel, final DBProperty property) {
        synchronized (this) {
            final IDBSource idbSource = new DBSourceSsh(sshTunnel, property);
            dataSources.put(idbSource.getName(), idbSource);
        }
    }

    public Connection getConnection(final String identifier) throws SQLException {
        final IDBSource idbSource = this.dataSources.get(identifier);

        synchronized (this) {
            if (idbSource != null) {
                return idbSource.connect();
            } else {
                throw new SQLException();
            }
        }
    }

    public void close(final String identifier) {
        final IDBSource idbSource = this.dataSources.get(identifier);

        synchronized (this) {
            if (idbSource != null) {
                idbSource.disconnect();
                this.dataSources.remove(identifier);
            }
        }
    }

    public void closeAll() {
        try {
            for (String dbKey : this.dataSources.keySet()) {
                this.dataSources.get(dbKey).disconnect();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public QueryResultSet executeSelect(final Connection conn, final String query) {
        try {
            this.isValidDB(conn);

            PreparedStatement pst = conn.prepareStatement(query);
            return new QueryResultSet(pst.executeQuery(), pst);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(final Connection conn, final String query) {
        try {
            this.isValidDB(conn);

            PreparedStatement pst = conn.prepareStatement(query);
            pst.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void isValidDB(final Connection conn) throws SQLException, InterruptedException {
        int retryCount = 5;
        while (!conn.isValid(DBHelper.DBM_CONNECT_TIMEOUT_SEC)) {
            retryCount -= 1;
            Thread.sleep(500);
            if (retryCount < 0) {
                throw new SQLException();
            }
        }
    }



    @Override
    public String toString() {
        return "DBManager{" +
                "dataSources=" + dataSources +
                '}';
    }
}
