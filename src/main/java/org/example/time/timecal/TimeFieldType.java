package org.example.time.timecal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.delegate.Func2;
import org.example.time.timeformat.TimeFormStd;

public enum TimeFieldType {

    YEAR(0, 4, LocalDateTime::plusYears, ChronoUnit.YEARS),
    MONTH(1, 6, LocalDateTime::plusMonths, ChronoUnit.MONTHS),
    DATE(2, 8, LocalDateTime::plusDays, ChronoUnit.DAYS),
    HOUR(3, 10, LocalDateTime::plusHours, ChronoUnit.HOURS),
    MINUTE(4, 12, LocalDateTime::plusMinutes, ChronoUnit.MINUTES),
    SECOND(5, 14, LocalDateTime::plusSeconds, ChronoUnit.SECONDS),
    MILLI_SECOND(6, 17, (time, addTime) -> time.plusNanos(addTime * 1_000_000L), ChronoUnit.MILLIS),
    MICRO_SECOND(7, 20, (time, addTime) -> time.plusNanos(addTime * 1_000L), ChronoUnit.MICROS),
    NANO_SECOND(8, 23, LocalDateTime::plusNanos, ChronoUnit.NANOS)
    ;

    private final static Map<Integer, TimeFieldType> TIME_FIELD = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(TimeFieldType::getField, Function.identity())));

    private final static Map<TimeFieldType, Func2<LocalDateTime, Long, LocalDateTime>> TIME_DELEGATE = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Function.identity(), TimeFieldType::getFunc))
    );

    public static TimeFieldType find(final int field) {
        if (!TIME_FIELD.containsKey(field)) {
            throw new IllegalArgumentException();
        }
        return TIME_FIELD.get(field);
    }

    public static String addTime(final TimeFieldType type, final LocalDateTime baseTime, final long addTime)
            throws Exception {
        LocalDateTime resultDateTime = TimeFieldType.findFunc(type).invoke(baseTime, addTime);
        if (type == MILLI_SECOND) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TimeFormStd.YYYYMMDDHH24MISSMILLI.getForm());
            return resultDateTime.format(formatter);
        }

        if (type == MICRO_SECOND) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TimeFormStd.YYYYMMDDHH24MISSMICRO.getForm());
            return resultDateTime.format(formatter);
        }

        if (type == NANO_SECOND) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TimeFormStd.YYYYMMDDHH24MISSNANO.getForm());
            return resultDateTime.format(formatter);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TimeFormStd.YYYYMMDDHH24MISS.getForm());
        return resultDateTime.format(formatter);

    }

    public static Func2<LocalDateTime, Long, LocalDateTime> findFunc(final TimeFieldType type) {
        if (!TIME_DELEGATE.containsKey(type)) {
            throw new IllegalArgumentException();
        }
        return TIME_DELEGATE.get(type);
    }

    private final int field;
    private final int length;
    private final Func2<LocalDateTime, Long, LocalDateTime> func;

    private final ChronoUnit chronoUnit;

    TimeFieldType(int field, int length, Func2<LocalDateTime, Long, LocalDateTime> func, ChronoUnit chronoUnit) {
        this.field = field;
        this.length = length;
        this.func = func;
        this.chronoUnit = chronoUnit;
    }

    public int getField() {
        return field;
    }

    public int getLength() {
        return length;
    }

    public Func2<LocalDateTime, Long, LocalDateTime> getFunc() {
        return func;
    }

    public ChronoUnit getChronoUnit() {
        return chronoUnit;
    }
}
