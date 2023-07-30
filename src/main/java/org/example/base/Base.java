package org.example.base;

public enum Base{
    BLANK(" "),
    NONE(""),
    ZERO("0"),
    ONE("1");

    private final String value;

    Base(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
