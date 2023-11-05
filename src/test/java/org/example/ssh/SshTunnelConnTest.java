//package org.example.ssh;
//
//import org.junit.jupiter.api.Test;
//
//class SshTunnelConnTest {
//
//    @Test
//    public void sshTestTo() {
//        SshConnInfo ubuntu = new SshConnInfo(
//                "52.62.26.84",
//                22,
//                "ubuntu",
//                "ubuntu"
//        );
//
//        SshTunnelConn ec2SshTunnelConnector = new SshTunnelConn(ubuntu);
//        ec2SshTunnelConnector.connect();
//        System.out.println("Connection Ok");
//
//        ec2SshTunnelConnector.disconnect();
//
//    }
//
//}