package org.example.time.timeformat;

public enum E_TimeFormPretty implements E_TimeForm {
    HOUR_MINUTE("hh:mm"),
    HOUR_MINUTE_SECOND("hh:mm:ss"),
    HOUR_MINUTE_SECOND_MILLISECOND("hh:mm:ss.SSS"),
    TWELVE_HOUR_CLOCK("hh:mm a"),
    TWENTY_FOUR_HOUR_CLOCK("HH:mm"),
    DATE("yyyy-MM-dd"),
    DATE_HOUR_MINUTE("yyyy-MM-dd hh:mm"),
    DATE_HOUR_MINUTE_SECOND("yyyy-MM-dd hh:mm:ss"),
    DATE_HOUR_MINUTE_SECOND_MILLISECOND("yyyy-MM-dd hh:mm:ss.SSS"),
    ISO_INSTANT("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


    private final String form;

    E_TimeFormPretty(String format) {
        this.form = format;
    }

    @Override
    public String getForm() {
        return form;
    }
}
