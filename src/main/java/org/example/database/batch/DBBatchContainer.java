package org.example.database.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

public class DBBatchContainer {
    private static final Logger LOG = Logger.getLogger(DBBatchContainer.class);

    private final List<DBBatchData> batchData;

    public DBBatchContainer(final DBBatchData batchData) {
        this(new ArrayList<>(List.of(batchData)));
    }

    public DBBatchContainer(final List<DBBatchData> batchData) {
        this.batchData = batchData;
    }

    public DBBatchContainer addBatch(final Connection connection, final String query, final Object[] param) {
        List<Object[]> params = new ArrayList<>();
        params.add(param);
        return this.addBatch(connection, query, params);
    }

    public DBBatchContainer addBatch(final Connection connection, final String query, final List<Object[]> params) {
        return this.addBatch(connection, query, params, 100);
    }

    public DBBatchContainer addBatch(final Connection connection, final String query, final Object[] param, int batchSize) {
        List<Object[]> params = new ArrayList<>();
        params.add(param);
        return this.addBatch(connection, query, params, batchSize);
    }

    public DBBatchContainer addBatch(final Connection connection, final String query, final List<Object[]> params, final int batchSize) {
        final DBBatchData dbBatchData = new DBBatchData(connection, query, params, batchSize);
        this.batchData.add(dbBatchData);

        return this;
    }

    public void executeBatch() {
        try{
            PreparedStatement preparedStatement = null;

            for (DBBatchData batch : batchData) {
                int tmpBatchCount = 0;

                final Connection connection = batch.getConnection();

                final boolean autoCommit = connection.getAutoCommit();
                if (autoCommit) connection.setAutoCommit(false);

                final String query = batch.getQuery();
                LOG.debug("[EXECUTE QUERY] " + query);
                preparedStatement = connection.prepareStatement(query);

                for (Object[] param : batch.getParamsList()) {
                    this.setParams(preparedStatement, param);
                    tmpBatchCount += 1;

                    if ((tmpBatchCount % batch.getBatchSize()) == 0) {
                        int[] results = preparedStatement.executeBatch();

                        if (batch.getBatchSize() != Arrays.stream(results).sum())
                            throw new SQLException();

                        connection.commit();
                        preparedStatement.clearBatch();
                    }
                }
                if (preparedStatement != null && (tmpBatchCount % batch.getBatchSize()) != 0) {
                    int[] results = preparedStatement.executeBatch();
                    if ((tmpBatchCount % batch.getBatchSize()) != Arrays.stream(results).sum())
                        throw new SQLException();

                    connection.commit();
                    preparedStatement.clearBatch();
                    preparedStatement.close();
                }

                if (autoCommit) connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void setParams(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        preparedStatement.addBatch();
        preparedStatement.clearParameters();
    }


    public List<DBBatchData> getBatchData() {
        return Collections.unmodifiableList(batchData);
    }

}
