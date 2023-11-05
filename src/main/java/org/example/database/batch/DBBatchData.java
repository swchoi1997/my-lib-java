package org.example.database.batch;

import java.sql.Connection;
import java.util.List;

public class DBBatchData {

    private final Connection connection;
    private final String query;
    private final List<Object[]> paramsList;
    private final int batchSize;


    public DBBatchData(Connection connection, String query, List<Object[]> paramsList, int batchSize) {
        this.connection = connection;
        this.query = query;
        this.paramsList = paramsList;
        this.batchSize = batchSize;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getQuery() {
        return query;
    }

    public List<Object[]> getParamsList() {
        return paramsList;
    }

    public int getBatchSize() {
        return batchSize;
    }
}
