package org.example.database.property;

import org.example.database.DBHelper;

public class MysqlDBProperty extends DBProperty {


    public MysqlDBProperty(final String dbName,
                           final String dbIp,
                           final String dbId,
                           final String dbPw,
                           final int dbPort) {
        super(DBPropertyType.MYSQL, dbName, dbIp, dbId, dbPw, dbPort,
                true, DBHelper.CONNECTION_TIMEOUT, 600000, 1800000, DBPropertyType.MYSQL.getPingQuery(),
                DBHelper.PING_SEC, 10, 60000);
    }

}
