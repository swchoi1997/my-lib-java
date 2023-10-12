package org.example.database;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DatabasePropertyType implements Serializable {

    ORACLE(0, "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@"),
    MYSQL(1, "com.mysql.jdbc.Driver", "jdbc:mysql://"),
    POSTGRES(2, "org.postgresql.Driver", "jdbc:postgresql://"),
    IGNITE(3, "org.apache.ignite.IgniteJdbcThinDriver", "jdbc:ignite:thin://"),
    ;

    private final static Map<Integer, DatabasePropertyType> DB_TYPE = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(DatabasePropertyType::getNumber, Function.identity())));

    private final int number;
    private final String driver;
    private final String connUrl;

    DatabasePropertyType(int number, String driver, String connUrl) {
        this.number = number;
        this.driver = driver;
        this.connUrl = connUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getDriver() {
        return driver;
    }

    public String getConnUrl() {
        return connUrl;
    }

    public static DatabasePropertyType find(final int number) {
        return DatabasePropertyType.DB_TYPE.get(number);
    }
}
