package org.example.time.timeformat;

import static org.example.time.timeformat.E_TimeFormPretty.DATE_HOUR_MINUTE_SECOND_MILLISECOND;
import static org.example.time.timeformat.E_TimeFormPretty.HOUR_MINUTE_SECOND_MILLISECOND;
import static org.example.time.timeformat.E_TimeFormStd.YYYY;
import static org.example.time.timeformat.E_TimeFormStd.YYYYMM;
import static org.example.time.timeformat.E_TimeFormStd.YYYYMMDD;
import static org.example.time.timeformat.E_TimeFormStd.YYYYMMDDHH24MISS;
import static org.example.time.timeformat.E_TimeFormStd.YYYYMMDDHH24MISSMILLI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.example.base.Base;

public interface E_TimeForm {

    String getForm();

    default DateTimeFormatter getDateTimeFormatter(final E_TimeForm eTimeForm) {
        return DateTimeFormatter.ofPattern(eTimeForm.getForm());
    }

    default boolean isSameTimeFormat(final E_TimeForm timeForm, final String strTime1, final String strTime2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeForm.getForm());
        try {
            simpleDateFormat.parse(strTime1);
            simpleDateFormat.parse(strTime2);
            return true;
        } catch (ParseException parseException) {
            return false;
        }
    }

    default String convertTimeFormat(final String time, final E_TimeForm timeForm) {
        String timeFit = "";
        DateTimeFormatter inputFormatter = null;
        LocalDateTime dateTime = null;
        if ((timeForm == YYYYMMDDHH24MISSMILLI) || (timeForm == DATE_HOUR_MINUTE_SECOND_MILLISECOND) ||
                (timeForm == HOUR_MINUTE_SECOND_MILLISECOND)) {
            timeFit = this.fillBlankTime(time, YYYYMMDDHH24MISSMILLI.getLen());
            inputFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHH24MISSMILLI.getForm());
        } else {
            timeFit = this.fillBlankTime(time, YYYYMMDDHH24MISS.getLen());
            inputFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHH24MISS.getForm());
        }

        dateTime = LocalDateTime.parse(timeFit, inputFormatter);
        return dateTime.format(DateTimeFormatter.ofPattern(timeForm.getForm()));
    }

    private String fillBlankTime(String time, int defaultLen) {
        if (time.length() > YYYYMMDDHH24MISS.getLen()) {
            return time.substring(Integer.parseInt(Base.ZERO.getValue()), defaultLen);
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
