package org.example.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ByteUtilsTest {

    @Test
    void toStringArrayReverseEndianTest() {
        byte[] test = new byte[5];
        test[0] = 1;
        test[1] = 2;
        test[2] = 3;
        test[3] = 4;
        test[4] = 5;

        byte[] reverseEndian = ByteUtils.reverse(test);
        System.out.println(Arrays.toString(reverseEndian));
    }

}