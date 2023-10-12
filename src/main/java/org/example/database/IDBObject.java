package org.example.database;

import java.io.Serializable;
import java.sql.Connection;
import org.example.ssh.SshTunnel;

public interface IDBObject extends Serializable, SshTunnel {

    public void connect(final boolean usePing) throws Exception;

    public boolean isConnect();

    public Connection getConnection();

    public QueryResultSet executeQuery(final String query);

}
