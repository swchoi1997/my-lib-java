package org.example.ssh;

public class SshConnInfo extends SshTunnelInfo{


    public SshConnInfo(final String host,
                       final String name,
                       final String passwd) {
        this(host, 22, name, passwd);
    }

    public SshConnInfo(final String host,
                       final int port,
                       final String name,
                       final String passwd) {
        super(host, port, name, passwd);
    }
}
