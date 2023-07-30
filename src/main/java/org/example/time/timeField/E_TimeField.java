package org.example.time.timeField;

public enum E_TimeField{

    NONE(-1, 0),
    YEAR(0, 4),
    MONTH(1, 2),
    DATE(2, 2),
    HOUR(3, 2),
    MINUTE(4, 2),
    SECOND(5, 2),
    MILLI_SECOND(6, 3),
    MICRO_SECOND(7, 3),
    NANO_SECOND(8, 3),
    ;

    private final int field;
    private final int length;

    E_TimeField(int field, int length) {
        this.field = field;
        this.length = length;
    }

    public int getField() {
        return field;
    }

    public int getLength() {
        return length;
    }
}
