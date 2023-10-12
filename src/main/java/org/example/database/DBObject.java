//package org.example.database;
//
//import java.sql.Connection;
//import org.apache.log4j.Logger;
//import org.example.ssh.SshTunnel;
//
//public abstract class DBObject implements IDBObject {
//    private static final Logger LOG = Logger.getLogger(DBObject.class);
//
//    private final DBProperty dbProperty;
//    private final Thread pingThread;
//    private final String jdbcDriver;
//    private final String baseUrl;
//    private final Connection conn;
//    private final SshTunnel sshTunnel;
//
//    public DBObject(final DBProperty dbProperty) {
//        this.dbProperty = dbProperty;
//        this.pingThread = this.getPingQuery(dbProperty);
//        this.jdbcDriver = dbProperty.getDbPropertyType().getDriver();
//        this.baseUrl = dbProperty.getDbPropertyType().getConnUrl();
//        this.conn
//
//    }
//
//    public DBObject(final DBProperty dbProperty,
//                    final Thread pingThread,
//                    final String jdbcDriver,
//                    final String baseUrl,
//                    final Connection conn,
//                    final SshTunnel sshTunnel) {
//        this.dbProperty = dbProperty;
//        this.pingThread = pingThread;
//        this.jdbcDriver = jdbcDriver;
//        this.baseUrl = baseUrl;
//        this.conn = conn;
//        this.sshTunnel = sshTunnel;
//    }
//
//    private Thread getPingQuery(final DBProperty dbProperty) {
//        return new DBPing(
//                this,
//                dbProperty.getDbName(),
//                dbProperty.getDbPropertyType().getPingQuery());
//    }
//
//    public DBProperty getDbProperty() {
//        return dbProperty;
//    }
//
//    public Thread getPingThread() {
//        return pingThread;
//    }
//
//    public String getJdbcDriver() {
//        return jdbcDriver;
//    }
//
//    public String getBaseUrl() {
//        return baseUrl;
//    }
//
//    public Connection getConn() {
//        return conn;
//    }
//
//    public SshTunnel getSshTunnel() {
//        return sshTunnel;
//    }
//}
