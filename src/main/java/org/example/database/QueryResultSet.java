package org.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    ResultSet은 Java의 JDBC (Java Database Connectivity) API에서 데이터베이스 쿼리 결과를 표현하는 객체입니다.
    SQL 쿼리를 실행한 후에 반환되는 결과 세트를 나타내며, 데이터에 순차적으로 액세스할 수 있는 방법을 제공합니다.
 */

public class QueryResultSet implements AutoCloseable {
    private final ResultSet resultSet;
    private final Statement statement;

    public QueryResultSet(ResultSet resultSet, Statement statement) {
        this.resultSet = resultSet;
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public void close() throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
    }
}
