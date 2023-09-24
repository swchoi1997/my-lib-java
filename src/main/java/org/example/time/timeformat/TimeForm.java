package org.example.time.timeformat;

import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TimeForm {
    private final String time;
    private final ITimeForm timeForm;

    public TimeForm() {
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(YYYYMMDDHH24MISS.getForm()));
        this.timeForm = YYYYMMDDHH24MISS;
    }

    public TimeForm(final String time) {
        this(time, YYYYMMDDHH24MISS);
    }

    public TimeForm(final String time, final ITimeForm timeForm) {
        this.time = timeForm.convertTimeFormat(time, timeForm);
        this.timeForm = timeForm;
    }

    public String getTime() {
        return time;
    }

    public ITimeForm getTimeForm() { return this.timeForm; }
    public String convertTimeFormat(final ITimeForm timeForm) {
        return timeForm.convertTimeFormat(this.time, timeForm);
    }

    public boolean isFitTimeFormat(final String timeString, final ITimeForm format) {
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
        TimeForm that = (TimeForm) o;
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
