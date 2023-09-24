package org.example.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

public class SshTunnelConnToKey extends SshTunnelConn {

    public SshTunnelConnToKey(final SshConnInfoToKey sshConnectionInfo) {
        super(sshConnectionInfo);
    }

    public SshTunnelConnToKey(final String host,
                              final String name,
                              final String passwd,
                              final String keyFile){
        this(new SshConnInfoToKey(host, 22, name, passwd, keyFile));
    }

    public SshTunnelConnToKey(final String host,
                              final int port,
                              final String name,
                              final String passwd,
                              final String keyFile){
        this(new SshConnInfoToKey(host, port, name, passwd, keyFile));
    }

    void makeSession() throws JSchException {
        super.session = this.makeJsch()
                .getSession(
                        this.sshConnectionInfo.getName(),
                        this.sshConnectionInfo.getHost(),
                        this.sshConnectionInfo.getPort());
        super.session.setConfig("StrictHostKeyChecking", "no");
    }

    JSch makeJsch() {
        JSch jSch = super.makeJsch();
        try {
            jSch.addIdentity(this.getSshConnectionInfo().getKeyFile());
            return jSch;
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    private SshConnInfoToKey getSshConnectionInfo() {
        return (SshConnInfoToKey) sshConnectionInfo;
    }
}
