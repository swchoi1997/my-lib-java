package org.example.utils;

import org.example.base.Base;

import java.util.stream.IntStream;

public class ByteUtils {

    private ByteUtils() { throw new UnsupportedOperationException();}

    public static String toStr(final String[] bytes) {
        return String.join(Base.NONE.getValue(), bytes);
    }

    public static String[] toStringArray(final byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> {
                    int unsignedByte = bytes[i] & 0xFF;
                    return String.format("%02x", unsignedByte);
                })
                .toArray(String[]::new);
    }

    public static String[] toStringArrayReverseEndian(final byte[] bytes) {
        return ByteUtils.toStringArray(ByteUtils.reverse(bytes));
    }


    public static byte[] reverse(final byte[] bytes) {
        for (int i = 0; i < bytes.length / 2; i++) {
            byte tmp = bytes[bytes.length - (i + 1)];
            bytes[bytes.length - (i + 1)] = bytes[i];
            bytes[i] = tmp;
        }
        return bytes;
    }

    public static int[] toUnsignedArray(final byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .map(i -> bytes[i] & 0xFF)
                .toArray();
    }

//    public static String[] toStringArrayWithEndian(final byte[] bytes, final ByteOrder endian) {
//        ByteBuffer order = ByteBuffer.wrap(bytes).order(endian);
//        return ByteUtils.toStringArray(ByteUtils.reverse(bytes));
//    }




    public static <T extends Number> byte[] convertToLittleEndian(T value) {
        int size = getSizeOfNumber(value);
        byte[] bytes = new byte[size];

        long longValue = value.longValue();

        for (int i = 0; i < size; i++) {
            bytes[i] = (byte) (longValue >> (8 * i));
        }

        return bytes;
    }

    public static <T extends Number> byte[] convertToBigEndian(T value) {
        int size = getSizeOfNumber(value);
        byte[] bytes = new byte[size];

        long longValue = value.longValue();

        for (int i = size - 1; i >= 0; i--) {
            bytes[i] = (byte) (longValue & 0xFF);
            longValue >>= 8;
        }

        return bytes;
    }

    private static <T extends Number> int getSizeOfNumber(T value) {
        if (value instanceof Byte) return Byte.BYTES;
        if (value instanceof Short) return Short.BYTES;
        if (value instanceof Integer) return Integer.BYTES;
        if (value instanceof Long) return Long.BYTES;

        throw new IllegalArgumentException("Unsupported Number type.");
    }


}
