package org.example.ssh;

public abstract class SshTunnelInfo {

    private final String host;
    private final int port;
    private final String name;
    private final String passwd;

    public SshTunnelInfo(final String host,
                             final String name,
                             final String passwd) {
        this(host, 22, name, passwd);
    }

    public SshTunnelInfo(final String host,
                             final int port,
                             final String name,
                             final String passwd) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.passwd = passwd;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

}
