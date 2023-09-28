package org.example.time.timeformat;

import static org.example.time.timeformat.TimeFormPretty.DATE_HOUR_MINUTE_SECOND_MILLISECOND;
import static org.example.time.timeformat.TimeFormPretty.HOUR_MINUTE_SECOND_MILLISECOND;
import static org.example.time.timeformat.TimeFormStd.YYYY;
import static org.example.time.timeformat.TimeFormStd.YYYYMM;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDD;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISS;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSMICRO;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSMILLI;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSNANO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.example.base.Base;

public interface ITimeForm {

    String getForm();

    default DateTimeFormatter getDateTimeFormatter(final ITimeForm eTimeForm) {
        return DateTimeFormatter.ofPattern(eTimeForm.getForm());
    }

    default boolean isSameTimeFormat(final ITimeForm timeForm, final String strTime1, final String strTime2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeForm.getForm());
        try {
            simpleDateFormat.parse(strTime1);
            simpleDateFormat.parse(strTime2);
            return true;
        } catch (ParseException parseException) {
            return false;
        }
    }

    default String convertTimeFormatStr(final String time, final ITimeForm timeForm) {
        LocalDateTime localDateTime = this.convertTimeFormatLocal(time, timeForm);
        return localDateTime.format(DateTimeFormatter.ofPattern(timeForm.getForm()));
    }

    default LocalDateTime convertTimeFormatLocal(final TimeForm timeForm) {
        return this.convertTimeFormatLocal(timeForm.getTime(), timeForm.getTimeForm());
    }

    default LocalDateTime convertTimeFormatLocal(final String time, final ITimeForm timeForm) {
        String timeFit = "";
        DateTimeFormatter inputFormatter = null;

        if ((timeForm == YYYYMMDDHH24MISSMILLI) || (timeForm == DATE_HOUR_MINUTE_SECOND_MILLISECOND) ||
                (timeForm == HOUR_MINUTE_SECOND_MILLISECOND)) {
            timeFit = this.fillBlankTime(time, YYYYMMDDHH24MISSMILLI.getLen());
            inputFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHH24MISSMILLI.getForm());
        } else if (timeForm == YYYYMMDDHH24MISSMICRO) {
            timeFit = this.fillBlankTime(time, YYYYMMDDHH24MISSMICRO.getLen());
            inputFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHH24MISSMICRO.getForm());
        } else if (timeForm == YYYYMMDDHH24MISSNANO) {
            timeFit = this.fillBlankTime(time, YYYYMMDDHH24MISSNANO.getLen());
            inputFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHH24MISSNANO.getForm());
        } else {
            timeFit = this.fillBlankTime(time, YYYYMMDDHH24MISS.getLen());
            inputFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHH24MISS.getForm());
        }

        return LocalDateTime.parse(timeFit, inputFormatter);
    }

    private String fillBlankTime(String time, int defaultLen) {
        if (time.length() > YYYYMMDDHH24MISS.getLen()) {
            if (time.length() > defaultLen) {
                return time.substring(Integer.parseInt(Base.ZERO.getValue()), defaultLen);
            }
            return time + Base.ZERO.getValue().repeat(Math.abs(defaultLen - time.length()));

        }
        time = this.fillBlankDate(time);
        return time + Base.ZERO.getValue().repeat(Math.abs(defaultLen - time.length()));
    }

    private String fillBlankDate(String time) {
        return time.length() > YYYYMMDD.getLen() ? time : fileDate(time, "01");
    }

    private String fileDate(String time, final String fillStr) {
        if (time.length() < YYYY.getLen()) {
            return LocalDateTime.now().getYear() + fillStr.repeat(2);
        } else if (time.length() < YYYYMM.getLen()) {
            return time.substring(Integer.parseInt(Base.ZERO.getValue()), YYYY.getLen()) + fillStr.repeat(2);
        } else if (time.length() < YYYYMMDD.getLen()) {
            return time.substring(Integer.parseInt(Base.ZERO.getValue()), YYYYMM.getLen()) + fillStr.repeat(1);
        } else {
            return time;
        }
    }
}
