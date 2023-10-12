package org.example.database;

public final class DBHelper {
    public static int DBM_CONNECT_TIMEOUT_SEC = 10;
    public static final int CONNECTION_TIMEOUT = 300 * 100;
    public static final int PING_SEC = 300 * 1000; //300SEC 6Min
    public static final String PING_QUERY_ORACLE = " SELECT 1 FROM DUAL ";
    public static final String PING_QUERY = " SELECT 1 ";
}
