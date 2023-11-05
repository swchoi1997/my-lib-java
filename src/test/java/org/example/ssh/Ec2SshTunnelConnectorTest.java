//package org.example.ssh;
//
//import org.junit.jupiter.api.Test;
//
//class Ec2SshTunnelConnectorTest {
//
//    @Test
//    public void sshTestToEc2() {
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
//        System.out.println("Connection Ok");
//
//        ec2SshTunnelConnector.disconnect();
//
//    }
//
//}