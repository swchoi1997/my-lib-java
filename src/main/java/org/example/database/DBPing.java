package org.example.database;

import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DBPing extends Thread {
    private static final Logger LOG = Logger.getLogger(DBPing.class);

    private final IDBObject idbObject;
    private final String dbName;
    private final String pingQuery;


    public DBPing(final IDBObject idbObject,
                  final String pingQuery) {
        this(idbObject, "", pingQuery);
    }

    public DBPing(final IDBObject idbObject,
                  final String dbName,
                  final String pingQuery) {
        this.idbObject = idbObject;
        this.dbName = dbName;
        this.pingQuery = pingQuery;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                if (idbObject.getConnection() == null) {
                    return;
                }
                if (idbObject.getConnection().isClosed()) {
                    return;
                }

                idbObject.executeQuery(this.pingQuery);
                LOG.info(dbName + " : " + "DB PING OK");
                Thread.sleep(DBHelper.PING_SEC);

            } catch (SQLException e) {
                LOG.info("[SQLException] : " + e.getSQLState() + "  " + e.getMessage());
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                LOG.info("[InterruptedException] : " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
