package org.example.time.timeformat;

import java.time.format.DateTimeFormatter;

public enum E_TimeFormStd implements E_TimeForm{

    NONE("None", 0),
    YYYYMMDDHH24MISSMILLI("yyyyMMddHHmmssSSS", 17),
    YYYYMMDDHH24MISS("yyyyMMddHHmmss", 14),
    YYYYMMDDHH24MI("yyyyMMddHHmm", 12),
    YYYYMMDDHH24("yyyyMMddHH", 10),
    YYYYMMDD("yyyyMMdd" ,8),
    YYYYMM("yyyyMM", 6),
    YYYY("yyyy", 4);


    private final String form;
    private final int len;

    E_TimeFormStd(String form, int len) {
        this.form = form;
        this.len = len;
    }

    @Override
    public String getForm() {
        return form;
    }

    public int getLen() {
        return len;
    }
}
