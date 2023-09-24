package org.example.base;

public enum ExceptionMessage {
    ILLEGAL_ARGUMENT_EXCEPTION("[EXCEPTION] ILLEGAL ARGUMENT"),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
