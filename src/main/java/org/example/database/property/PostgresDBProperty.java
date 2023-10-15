package org.example.database.property;

import org.example.database.help.DBHelper;

public class PostgresDBProperty extends DBProperty {


    public PostgresDBProperty(final String dbName,
                              final String dbIp,
                              final String dbId,
                              final String dbPw,
                              final int dbPort) {
        super(DBPropertyType.POSTGRES, dbName, dbIp, dbId, dbPw, dbPort,
                true, DBHelper.CONNECTION_TIMEOUT, 600000, 1800000, DBPropertyType.POSTGRES.getPingQuery(),
                DBHelper.PING_SEC, 10, 60000);
    }

}
