package org.example.utils;

public class NumberUtils {

    private NumberUtils() {
        throw new UnsupportedOperationException();
    }


    public static <T extends Number & Comparable<T>> boolean isInRange(final T value, final T minValue, final T maxValue) {
        return minValue.compareTo(value) <= 0 && value.compareTo(maxValue) <= 0;
    }


    public static <T extends Number> Integer getSize (final T value) {
        if (value instanceof Byte) {
            return Byte.BYTES;
        } else if (value instanceof Short) {
            return Short.BYTES;
        } else if (value instanceof Integer) {
            return Integer.BYTES;
        } else if (value instanceof Long) {
            return Long.BYTES;
        } else if (value instanceof Float) {
            return Float.BYTES;
        } else if (value instanceof Double) {
            return Double.BYTES;
        } else {
            throw new IllegalArgumentException("Unsupported Number type.");
        }
    }
}
