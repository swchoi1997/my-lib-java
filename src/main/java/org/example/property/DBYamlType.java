package org.example.property;

import java.util.Map;

public enum DBYamlType {
    BASE("datasource"),
    URL("url"),
    DRIVER_CLASS_NAME("driver-class-name"),
    DB_ID("username"),
    DB_PW("password"),
    AUTO_COMMIT("autoCommit"),
    CONNECTION_TIMEOUT("connectionTimeout"),
    IDLE_TIME("idleTimeout"),
    MAX_LIFE_TIME("maxLifetime"),
    CONNECTION_QUERY("connectionQuery"),
    VALIDATION_TIMEOUT("validationTimeout"),
    MAX_POOL_SIZE("maximumPoolSize"),
    LEAK_THRESHOLD("leakDetectionThreshold"),
    ;

    private final String yamlName;

    DBYamlType(String yamlName) {
        this.yamlName = yamlName;
    }

    public String getYamlName() {
        return yamlName;
    }
}
