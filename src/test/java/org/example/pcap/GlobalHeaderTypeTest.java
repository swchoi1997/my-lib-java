package org.example.pcap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalHeaderTypeTest {

    @Test
    public void tesT() {

        byte a = (byte) 128;
        System.out.println(a);
        System.out.println(a & 0xFF);
        System.out.println(a + 256);




        byte b = (byte) 127;
        System.out.println(b);
        System.out.println(b & 0xFF);




        // 1 2 4 8 16 32 64 128
    }

}