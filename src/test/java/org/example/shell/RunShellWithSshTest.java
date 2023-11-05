//package org.example.shell;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.example.ssh.SshConnInfo;
//import org.example.ssh.SshTunnel;
//import org.example.ssh.SshTunnelConn;
//import org.junit.jupiter.api.Test;
//
//class RunShellWithSshTest {
//
//    @Test
//    public void SshCommandTest() {
//        SshTunnel ssh = new SshTunnelConn(
//                new SshConnInfo("52.62.26.84",
//                        "ubuntu", "ubuntu")
//        );
//        RunShellWithSsh shell = new RunShellWithSsh(ssh);
//
//        System.out.println(shell.run("whoami"));
//
//
//    }
//
//}