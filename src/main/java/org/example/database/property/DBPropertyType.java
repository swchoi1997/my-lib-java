package org.example.database.property;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.example.database.DBHelper;

public enum DBPropertyType implements Serializable {

    ORACLE(0, "oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@", DBHelper.PING_QUERY_ORACLE),
    MYSQL(1, "mysql", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://", DBHelper.PING_QUERY),
    POSTGRES(2, "postgres", "org.postgresql.Driver", "jdbc:postgresql://", DBHelper.PING_QUERY),
    IGNITE(3, "ignite", "org.apache.ignite.IgniteJdbcThinDriver", "jdbc:ignite:thin://", DBHelper.PING_QUERY),
    ;

    private final static Map<Integer, DBPropertyType> DB_TYPE = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(DBPropertyType::getNumber, Function.identity())));

    private final int number;
    private final String name;
    private final String driver;
    private final String connUrl;
    private final String pingQuery;

    DBPropertyType(final int number,
                   final String name,
                   final String driver,
                   final String connUrl,
                   final String pingQuery) {

        this.number = number;
        this.name = name;
        this.driver = driver;
        this.connUrl = connUrl;
        this.pingQuery = pingQuery;
    }

    public int getNumber() {
        return number;
    }

    public String getName() { return this.name; }

    public String getDriver() {
        return driver;
    }

    public String getConnUrl() {
        return connUrl;
    }

    public String getPingQuery() {
        return pingQuery;
    }

    public static DBPropertyType find(final int number) {
        return DBPropertyType.DB_TYPE.get(number);
    }
}
