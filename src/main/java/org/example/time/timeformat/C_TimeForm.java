package org.example.time.timeformat;

import static org.example.time.timeformat.E_TimeFormStd.YYYYMMDDHH24MISS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class C_TimeForm {
    private final String time;
    private final E_TimeForm timeForm;

    public C_TimeForm() {
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(YYYYMMDDHH24MISS.getForm()));
        this.timeForm = YYYYMMDDHH24MISS;
    }

    public C_TimeForm(final String time) {
        this(time, YYYYMMDDHH24MISS);
    }

    public C_TimeForm(final String time, final E_TimeForm timeForm) {
        this.time = timeForm.convertTimeFormat(time, timeForm);
        this.timeForm = timeForm;
    }

    public String getTime() {
        return time;
    }

    public E_TimeForm getTimeForm() { return this.timeForm; }
    public String convertTimeFormat(final E_TimeForm timeForm) {
        return timeForm.convertTimeFormat(this.time, timeForm);
    }

    public boolean isFitTimeFormat(final String timeString, final E_TimeForm format) {
        if (timeString.length() != format.getForm().length()) {
            return false;
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format.getForm());
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(timeString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        C_TimeForm that = (C_TimeForm) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    @Override
    public String toString() {
        return "C_TimeForm{" +
                "format='" + time + '\'' +
                '}';
    }
}
