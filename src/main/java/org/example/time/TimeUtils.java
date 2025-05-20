package org.example.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.example.time.timeformat.ITimeForm;

public class TimeUtils {

    private TimeUtils() {
        throw new UnsupportedOperationException();
    }

    public static String now(ITimeForm format, ZoneId zone) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(format.getForm()));
    }

    public static long toEpochMillis(String time, ITimeForm format, ZoneId zone) {
        LocalDateTime localDateTime = format.convertTimeFormatLocal(time, format);
        ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
        return zonedDateTime.toInstant().toEpochMilli();
    }

    public static String fromEpochMillis(long epochMillis, ITimeForm format, ZoneId zone) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), zone);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(format.getForm()));
    }
}
