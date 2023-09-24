package org.example.ssh;

import com.jcraft.jsch.Session;
import org.example.delegate.Action;

public interface SshTunnel {

    SshTunnel connect();

    SshTunnel connect(final Action action);


    SshTunnel portForwarding(final int port);

    boolean disconnect();

    Session getSession();
}
