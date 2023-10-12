package org.example.database.property;

import java.io.Serializable;
import org.example.database.DBHelper;

public class DBProperty implements Serializable {

    private final DBPropertyType dbPropertyType;
    private final String dbName;
    private final String dbIp;
    private final String dbId;
    private final String dbPw;
    private final int dbPort;
    private final boolean isAutoCommit;
    private final long connectionTimeout;
    private final long idleTimeout;
    private final long maxLifetime;
    private final String connectionTestQuery;
    private final long validationTimeout;
    private final int maximumPoolSize;
    private final long leakDetectionThreshold;

    public DBProperty(final DBPropertyType dbPropertyType,
                      final String dbName,
                      final String dbIp,
                      final String dbId,
                      final String dbPw,
                      final int dbPort) {
        this(dbPropertyType,
                dbName,
                dbIp,
                dbId,
                dbPw,
                dbPort,
                true,
                DBHelper.CONNECTION_TIMEOUT,
                600000,
                1800000,
                dbPropertyType.getPingQuery(),
                DBHelper.PING_SEC,
                10,
                60000);
    }

    public DBProperty(final DBPropertyType dbPropertyType,
                      final String dbName,
                      final String dbIp,
                      final String dbId,
                      final String dbPw,
                      final int dbPort,
                      final boolean isAutoCommit,
                      final long connectionTimeout,
                      final long idleTimeout,
                      final long maxLifetime,
                      final String connectionTestQuery,
                      final long validationTimeout,
                      final int maximumPoolSize,
                      final long leakDetectionThreshold) {
        this.dbPropertyType = dbPropertyType;
        this.dbName = dbName;
        this.dbIp = dbIp;
        this.dbId = dbId;
        this.dbPw = dbPw;
        this.dbPort = dbPort;
        this.isAutoCommit = isAutoCommit;
        this.connectionTimeout = connectionTimeout;
        this.idleTimeout = idleTimeout;
        this.maxLifetime = maxLifetime;
        this.connectionTestQuery = connectionTestQuery;
        this.validationTimeout = validationTimeout;
        this.maximumPoolSize = maximumPoolSize;
        this.leakDetectionThreshold = leakDetectionThreshold;
    }
//
//    public DBProperty setForwardPort(final int forwardPort) {
//        return new DBProperty(this.dbPropertyType, this.dbName, this.dbIp, this.dbId, this.dbPw, forwardPort);
//    }

    public DBPropertyType getDbPropertyType() {
        return dbPropertyType;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbIp() {
        return dbIp;
    }

    public String getDbId() {
        return dbId;
    }

    public String getDbPw() {
        return dbPw;
    }

    public int getDbPort() {
        return dbPort;
    }

    public boolean isAutoCommit() {
        return isAutoCommit;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public long getMaxLifetime() {
        return maxLifetime;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public long getValidationTimeout() {
        return validationTimeout;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public long getLeakDetectionThreshold() {
        return leakDetectionThreshold;
    }

    public String getDataSourceClassName() {
        return this.dbPropertyType.getDriver();
    }

}
