package org.example.os;

public class OsUtils {

    public static String currentOs() {
        final String os = System.getProperty("os.name");
        return os;
    }

}
