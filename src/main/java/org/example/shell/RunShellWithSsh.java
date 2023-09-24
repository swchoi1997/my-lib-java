package org.example.shell;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.example.ssh.SshTunnel;

public class RunShellWithSsh {
    private static final Logger LOG = Logger.getLogger(RunShellWithSsh.class);

    private final SshTunnel sshTunnel;

    public RunShellWithSsh(SshTunnel sshTunnel) {
        this.sshTunnel = sshTunnel;
        this.sshTunnel.connect();
    }

    public String run(final String command) {
        Session session = this.sshTunnel.getSession();
        if (!session.isConnected()) {
            LOG.error("[ERROR] SSH IS NOT CONNECTED");
            throw new RuntimeException();
        }
        String responseString = "";
        Channel channel = null;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            ByteArrayOutputStream response = new ByteArrayOutputStream();
            channel.setOutputStream(response);
            channel.connect();

            long startTime = System.currentTimeMillis();
            long timeout = 10000;

            while (channel.isConnected()) {
                Thread.sleep(100);
                if (System.currentTimeMillis() - startTime > timeout) {
                    LOG.error("[ERROR] Connection TimeOut");
                    throw new RuntimeException();
                }
            }

            responseString = response.toString();

        } catch (JSchException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }

        return responseString;
    }
}
