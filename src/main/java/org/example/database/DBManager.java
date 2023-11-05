package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.example.database.batch.DBBatchContainer;
import org.example.database.batch.DBBatchData;
import org.example.database.help.DBHelper;
import org.example.database.object.DBObject;
import org.example.database.property.DBProperty;
import org.example.database.source.QueryResultSet;
import org.example.ssh.SshTunnel;

public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);
    private final ConcurrentHashMap<String, DBObject> dataSources = new ConcurrentHashMap<>();

    private DBManager() {}

    public static synchronized DBManager getInstance() {
        return DBManager.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final DBManager INSTANCE = new DBManager();
    }

    public void append(final DBProperty property) {
        synchronized (this) {
            final DBObject object = new DBObject(property);
            dataSources.put(object.getName(), object);
        }
    }

    public void append(final SshTunnel sshTunnel, final DBProperty property) {
        synchronized (this) {
            final DBObject object = new DBObject(sshTunnel, property);
            dataSources.put(object.getName(), object);
        }
    }

    public Connection getConnection(final String identifier) {
        DBObject object = this.dataSources.get(identifier);

        synchronized (this) {
            if (object != null) {
                return object.getConnection();
            } else {
                throw new NullPointerException();
            }
        }
    }

    public void close(final String identifier) {
        final DBObject object = this.dataSources.get(identifier);

        synchronized (this) {
            if (object != null) {
                object.close();
                this.dataSources.remove(identifier);
            }
        }
    }

    public void closeAll() {
        try {
            for (String dbKey : this.dataSources.keySet()) {
                this.dataSources.get(dbKey).close();
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

            try (PreparedStatement pst = conn.prepareStatement(query)){
                pst.executeUpdate();
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public DBBatchContainer makeBatch(final Connection conn, final String query, final Object[] params, final int batchSize) {
        List<Object[]> paramsList = new ArrayList<>();
        paramsList.add(params);

        return this.makeBatch(conn, query, paramsList, batchSize);
    }

    public DBBatchContainer makeBatch(final Connection conn, final String query, final List<Object[]> params, final int batchSize) {
        return new DBBatchContainer(new DBBatchData(conn, query, params, batchSize));
    }

    public void executeBatch(final DBBatchContainer batchContainer) {
        batchContainer.executeBatch();
    }

    public void commit (final Connection conn) {
        try{
            conn.commit();
        } catch (SQLException e) {
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
