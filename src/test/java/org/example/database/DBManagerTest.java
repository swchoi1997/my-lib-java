package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.example.database.batch.DBBatchContainer;
import org.example.database.batch.DBBatchData;
import org.example.database.property.DBProperty;
import org.example.database.property.DBPropertyType;
import org.example.database.source.QueryResultSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBManagerTest {

    List<String> alphabet =
            new ArrayList<>(List.of("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));


    private static Random random = new Random();

    @Test
    void DBConnTest() throws SQLException {
        DBManager dbManager = DBManager.getInstance();
//
//        SshConnInfoToKey ubuntu = new SshConnInfoToKey(
//                "52.62.26.84",
//                22,
//                "ubuntu",
//                "",
//                "/Users/forest_choi/pem/test_serv.pem"
//        );
//
//        SshTunnelConnToKey ec2SshTunnelConnector = new SshTunnelConnToKey(ubuntu);
//        ec2SshTunnelConnector.connect();
//
//        DBProperty dbProperty =
//                new DBProperty(DBPropertyType.MYSQL, "test", "52.62.26.84", "oracle", "oracle", 3306);



        DBProperty mysqlLocal =
                new DBProperty(DBPropertyType.MYSQL, "multiTest", "127.0.0.1", "root", "oracle", 33306);
        DBProperty postgresLocal =
                new DBProperty(DBPropertyType.POSTGRES, "multitest", "127.0.0.1", "oracle", "oracle", 35432);

//        dbManager.append(ec2SshTunnelConnector, dbProperty);
        dbManager.append(mysqlLocal);
        dbManager.append(postgresLocal);

//        Connection connection1 = dbManager.getConnection("52.62.26.84:3306/test");
        Connection connection2 = dbManager.getConnection("127.0.0.1:33306/multiTest");
        Connection connection3 = dbManager.getConnection("127.0.0.1:35432/multitest");
        Connection connection4 = dbManager.getConnection("127.0.0.1:33306/multiTest");

        String query = "insert into test1 (id, test1) values(7, \"java23\")";
        dbManager.executeUpdate(connection2, query);

        String query2 = "select * from test1 where id = 7";
        QueryResultSet queryResultSet = dbManager.executeSelect(connection4, query2);

        while (queryResultSet.getResultSet().next()) {
            System.out.println(queryResultSet.getResultSet().getString("id") + "  " +
                    queryResultSet.getResultSet().getString("test1"));
        }

        dbManager.closeAll();
    }


    @Test
    @DisplayName("Insert Query But Not Commit")
    void DBInsertNotCommitTest() throws SQLException {
        DBManager dbManager = DBManager.getInstance();

        DBProperty mysqlLocal =
                new DBProperty(DBPropertyType.MYSQL, "multiTest", "127.0.0.1", "root", "oracle", 33306, false);
        dbManager.append(mysqlLocal);

        Connection connection2 = dbManager.getConnection("127.0.0.1:33306/multiTest");
        Connection connection4 = dbManager.getConnection("127.0.0.1:33306/multiTest");

        String randomWord = this.getRandomWord();
        int randomNum = this.getRandomNum();
        String insertQuery =  "INSERT INTO Example (Name, Age, Address) "
                + "VALUES ('" + randomWord + "', " + randomNum + ", '"
                + randomWord + "')";
        dbManager.executeUpdate(connection2, insertQuery);

        String selectQuery = "SELECT * "
                + "FROM Example "
                + "WHERE Name = '" + randomWord
                + "' AND Age = " + randomNum;

        QueryResultSet queryResultSet = dbManager.executeSelect(connection4, selectQuery);

        Assertions.assertThat(queryResultSet.getResultSet().next()).isFalse();

        dbManager.closeAll();
    }

    private String getRandomWord() {
        Collections.shuffle(this.alphabet);

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            word.append(this.alphabet.get(i));
        }
        return String.valueOf(word);
    }


    @Test
    @DisplayName("Insert Query Commit")
    void DBInsertCommitTest() throws SQLException {
        DBManager dbManager = DBManager.getInstance();

        DBProperty mysqlLocal =
                new DBProperty(DBPropertyType.MYSQL, "multiTest", "127.0.0.1", "root", "oracle", 33306, false);
        dbManager.append(mysqlLocal);

        Connection connection2 = dbManager.getConnection("127.0.0.1:33306/multiTest");
        Connection connection4 = dbManager.getConnection("127.0.0.1:33306/multiTest");

        String randomWord = this.getRandomWord();
        int randomNum = this.getRandomNum();
        String insertQuery =  "INSERT INTO Example (Name, Age, Address) "
                + "VALUES ('" + randomWord + "', " + randomNum + ", '"
                + randomWord + "')";
        dbManager.executeUpdate(connection2, insertQuery);
        dbManager.commit(connection2);

        String selectQuery = "SELECT * "
                + "FROM Example "
                + "WHERE Name = '" + randomWord
                + "' AND Age = " + randomNum;

        QueryResultSet queryResultSet = dbManager.executeSelect(connection4, selectQuery);


        Assertions.assertThat(queryResultSet.getResultSet().next()).isTrue();

        dbManager.closeAll();
    }


    @Test
    @DisplayName("Batch Insert Test")
    void batchTest() {
        DBManager dbManager = DBManager.getInstance();

        DBProperty mysqlLocal =
                new DBProperty(DBPropertyType.MYSQL, "multiTest", "127.0.0.1", "root", "oracle", 33306, false);
        dbManager.append(mysqlLocal);

        Connection connection2 = dbManager.getConnection("127.0.0.1:33306/multiTest");

        List<Object[]> params = new ArrayList<>();

        String insertQuery = "INSERT INTO Example (Name, Age, Address) VALUES (?, ?, ?)";
        for (int i = 0; i < 1000; i++) {
            String randomWord = this.getRandomWord();
            int randomNum = this.getRandomNum();
            Object[] objects = {randomWord, randomNum, randomWord};

            params.add(objects);
        }
        DBBatchData datas = new DBBatchData(connection2, insertQuery, params, 100);

        DBBatchContainer dbBatchContainer = new DBBatchContainer(datas);

        dbManager.executeBatch(dbBatchContainer);

        dbManager.closeAll();
    }


    private int getRandomNum() {
        return random.nextInt();
    }

}