package org.example.time.timecal;

import static org.example.time.timecal.TimeFieldType.MICRO_SECOND;
import static org.example.time.timecal.TimeFieldType.MILLI_SECOND;
import static org.example.time.timecal.TimeFieldType.NANO_SECOND;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISS;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSMICRO;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSMILLI;
import static org.example.time.timeformat.TimeFormStd.YYYYMMDDHH24MISSNANO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import org.example.delegate.Action1;
import org.example.time.timeformat.ITimeForm;
import org.example.time.timeformat.TimeForm;
import org.example.time.timeformat.TimeFormStd;

public class TimeStringCal {


    private final TimeForm timeForm;

    public TimeStringCal() {
        this.timeForm = new TimeForm();
    }

    public TimeStringCal(String timeValue) {
        this.timeForm = new TimeForm(timeValue);
    }

    public TimeStringCal(String timeValue, ITimeForm iTimeForm) {
        this.timeForm = new TimeForm(timeValue, iTimeForm);
    }


    public String getTime() {
        return timeForm.getTime();
    }

    public TimeForm getTimeForm() {
        return timeForm;
    }

    public ITimeForm getTimeFormat(){ return timeForm.getTimeForm(); }

    public Long compareTime(final TimeStringCal compareTime) {
        return this.compareTime(compareTime, ChronoUnit.SECONDS);
    }

    public Long compareTime(final TimeStringCal compareTime, final ChronoUnit chronoUnit) {
        LocalDateTime base = this.getTimeForm().convertTimeFormatLocal(this.getTimeFormat());
        LocalDateTime compare = compareTime.getTimeForm().convertTimeFormatLocal(compareTime.getTimeFormat());

        return chronoUnit.between(compare, base);
    }


    public boolean eq(final TimeStringCal compareTime) {
        return Long.parseLong(this.getTime()) - Long.parseLong(compareTime.getTime()) == 0L;
    }

    public TimeStringCal next(final TimeFieldType type) throws Exception {
        return this.calculateTime(type, 1);
    }

    public TimeStringCal before(final TimeFieldType type) throws Exception{
        return this.calculateTime(type, -1);
    }

    public TimeStringCal calculateTime(final TimeFieldType type, final long addValue) throws Exception {
        return new TimeStringCal(TimeFieldType.addTime(type, this.stringTimeToLocalTime(type), addValue));
    }

    private LocalDateTime stringTimeToLocalTime(final TimeFieldType type) {
        if (type == MILLI_SECOND) {
            return this.timeForm.convertTimeFormatLocal(YYYYMMDDHH24MISSMILLI);
        }
        if (type == MICRO_SECOND) {
            return this.timeForm.convertTimeFormatLocal(YYYYMMDDHH24MISSMICRO);
        }
        if (type == NANO_SECOND) {
            return this.timeForm.convertTimeFormatLocal(YYYYMMDDHH24MISSNANO);
        }
        return this.timeForm.convertTimeFormatLocal(YYYYMMDDHH24MISS);
    }

    public static void coroutine(final String startTime, final String endTime,
                                 final TimeFieldType type, final long interval, final Action1<TimeStringCal> action) {

        TimeStringCal start = new TimeStringCal(startTime);
        TimeStringCal end = new TimeStringCal(endTime);

        start = new TimeStringCal(start.getTimeForm().convertTimeFormatStr(end.getTimeFormat()));

        Long compareTime = end.compareTime(start, type.getChronoUnit());
        if (compareTime == 0L) return;

        long intervalCnt = interval;
        if (compareTime < 0L)
            intervalCnt = -interval;

        while (0L != end.compareTime(start, type.getChronoUnit())) {
            try {
                action.invork(start);
                start = start.calculateTime(type, intervalCnt);
                Thread.sleep(1);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
}
