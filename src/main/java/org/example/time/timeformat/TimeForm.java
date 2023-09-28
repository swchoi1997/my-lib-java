package org.example.time.timeformat;

import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISS;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSMICRO;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSMILLI;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSNANO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TimeForm {
    private final String time;
    private final ITimeForm timeForm;

    public TimeForm() {
        this(LocalDateTime.now().format(DateTimeFormatter.ofPattern(YYYYMMDDHH24MISS.getForm())), YYYYMMDDHH24MISS);
    }

    public TimeForm(final String time) {
        final TimeFormStd timeFormStd = this.checkTimeForm(time);
        this.time = timeFormStd.convertTimeFormatStr(time, timeFormStd);
        this.timeForm = timeFormStd;

    }

    public TimeForm(final String time, final ITimeForm timeForm) {
        this.time = timeForm.convertTimeFormatStr(time, timeForm);
        this.timeForm = timeForm;
    }

    private TimeFormStd checkTimeForm(final String time) {
        if (time.length() > YYYYMMDDHH24MISS.getLen() && time.length() <= YYYYMMDDHH24MISSMILLI.getLen())
            return YYYYMMDDHH24MISSMILLI;

        if (time.length() > YYYYMMDDHH24MISSMILLI.getLen() && time.length() <= YYYYMMDDHH24MISSMICRO.getLen())
            return YYYYMMDDHH24MISSMICRO;

        if (time.length() > YYYYMMDDHH24MISSMICRO.getLen())
            return YYYYMMDDHH24MISSNANO;

        return YYYYMMDDHH24MISS;
    }

    public String getTime() {
        return time;
    }

    public ITimeForm getTimeForm() { return this.timeForm; }

    public String convertTimeFormatStr(final ITimeForm timeForm) {
        return timeForm.convertTimeFormatStr(this.time, timeForm);
    }

    public LocalDateTime convertTimeFormatLocal(final ITimeForm timeForm) {
        return timeForm.convertTimeFormatLocal(this.time, timeForm);
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
