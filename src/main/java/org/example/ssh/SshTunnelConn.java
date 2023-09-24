package org.example.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.example.delegate.Action;

public class SshTunnelConn implements SshTunnel {

    final SshTunnelInfo sshConnectionInfo;
    Session session = null;

    public SshTunnelConn(final SshConnInfo sshConnectionInfo){
        this.sshConnectionInfo = sshConnectionInfo;
    }

    public SshTunnelConn(final String host,
                         final String name,
                         final String passwd){
        this(new SshConnInfo(host, 22, name, passwd));
    }

    public SshTunnelConn(final String host,
                         final int port,
                         final String name,
                         final String passwd){
        this(new SshConnInfo(host, port, name, passwd));
    }

    @Override
    public SshTunnel connect() {
        return this.connect(null);
    }

    @Override
    public SshTunnel connect(final Action action) {
        if (this.session != null) {
            if (this.session.isConnected()) {
                return this;
            }
        }

        try {
            this.makeSession();
            this.session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (action != null) {
            try {
                action.invork();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return this;
    }

    void makeSession() throws JSchException {
        this.session = this.makeJsch()
                .getSession(
                        this.sshConnectionInfo.getName(),
                        this.sshConnectionInfo.getHost(),
                        this.sshConnectionInfo.getPort());
        this.session.setPassword(this.sshConnectionInfo.getPasswd());
        this.session.setConfig("StrictHostKeyChecking", "no");
    }

    JSch makeJsch() {
        return new JSch();
    }

    @Override
    public SshTunnel portForwarding(final int port) {
        if (this.session == null) {
            throw new NullPointerException();
        }

        try {
            this.session.setPortForwardingL(
                    this.sshConnectionInfo.getPort(),
                    this.sshConnectionInfo.getHost(),
                    port);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public boolean disconnect() {
        if (this.session != null) {
            this.session.disconnect();
        }

        return true;
    }

    private SshConnInfo getSshConnectionInfo() {
        return (SshConnInfo) sshConnectionInfo;
    }

    public Session getSession() {
        return this.session;
    }
}
