package org.example.base;

public enum Base{
    BLANK(" "),
    NONE(""),
    DOT("\\."),
    ZERO("0"),
    ONE("1"),
    BYTE_LENGTH("4")
    ;

    private final String value;

    Base(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
