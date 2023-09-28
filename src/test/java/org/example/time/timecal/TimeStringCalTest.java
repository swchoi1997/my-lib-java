package org.example.time.timecal;

import static org.example.time.timecal.TimeFieldType.*;
import static org.example.time.timecal.TimeFieldType.MICRO_SECOND;
import static org.example.time.timecal.TimeFieldType.MILLI_SECOND;
import static org.example.time.timecal.TimeFieldType.NANO_SECOND;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.example.base.Base;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeStringCalTest {

    @Test
    public void GetCurrentTime() {
        TimeStringCal timeStringCal = new TimeStringCal();
        System.out.println(timeStringCal.getTime());
    }

    @ParameterizedTest(name = "time: {0}")
    @MethodSource("isValidTimeFormatAll")
    public void GetTimeFit(String time) {
        TimeStringCal timeStringCal = new TimeStringCal(time);
        System.out.println(timeStringCal.getTime());
    }

    static Stream<Arguments> isValidTimeFormatAll() {
        return Stream.of(
                arguments("2023"),
                arguments("202304"),
                arguments("20230405"),
                arguments("2023040501"),
                arguments("202304050320"),
                arguments("20230405000120"),
                arguments("20230405000120332"),
                arguments("20230"),
                arguments("2023040"),
                arguments("202304050"),
                arguments("20230405001"),
                arguments("2023040500123"),
                arguments("202304050012300")
        );
    }

    @ParameterizedTest(name = "time1: {0}, time2: {1}")
    @MethodSource("compareTime")
    public void compareTest(String time1, String time2) {
        TimeStringCal timeStringCal = new TimeStringCal(time1);
        TimeStringCal timeStringCal2 = new TimeStringCal(time2);

        System.out.println(timeStringCal2.compareTime(timeStringCal));


    }

    static Stream<Arguments> compareTime() {
        return Stream.of(
                arguments("20230405000120", "20230505120122"),
                arguments("20230405000120", "20230405000122"),
                arguments("20230405000120", "20230405000115"),
                arguments("20230405000120050", "20230405000120000"),
                arguments("20230405000120332", "20230405000120331"),
                arguments("20230405000120332", "20230405000120334"),
                arguments("202304050012300", "202304050014300"),
                arguments("20230405001230", "20230405001430")
//                arguments("202304050012303`32332332`", "20230405001430", 200L)
        );
    }

    @ParameterizedTest(name = "time1: {0}, time2: {1}, chronoUnit: {2}")
    @MethodSource("compareTime2")
    public void compareTest2(String time1, String time2, ChronoUnit chronoUnit) {
        TimeStringCal timeStringCal = new TimeStringCal(time1);
        TimeStringCal timeStringCal2 = new TimeStringCal(time2);

        System.out.println(timeStringCal2.compareTime(timeStringCal, chronoUnit));
    }

    static Stream<Arguments> compareTime2() {
        return Stream.of(
                arguments("20230405000120", "20230505120122", ChronoUnit.DAYS),
                arguments("20230405000120", "20230505120122", ChronoUnit.MONTHS),
                arguments("20230405000120", "20230505120122", ChronoUnit.HOURS),
                arguments("20230405000120", "20230505120122", ChronoUnit.MINUTES),
                arguments("20230405000120", "20230405000122", ChronoUnit.DAYS),
                arguments("20230405000120", "20230405000122", ChronoUnit.MONTHS),
                arguments("20230405000120", "20230405000122", ChronoUnit.HOURS),
                arguments("20230405000120", "20230405000122", ChronoUnit.MINUTES),
                arguments("20230405000120", "20230405000115", ChronoUnit.DAYS),
                arguments("20230405000120", "20230405000115", ChronoUnit.MONTHS),
                arguments("20230405000120", "20230405000115", ChronoUnit.HOURS),
                arguments("20230405000120", "20230405000115", ChronoUnit.MINUTES),
                arguments("20230405000120", "20230405000115", ChronoUnit.MICROS),
                arguments("20230405000120050", "20230405000120000", ChronoUnit.DAYS),
                arguments("20230405000120050", "20230405000120000", ChronoUnit.MONTHS),
                arguments("20230405000120050", "20230405000120000", ChronoUnit.HOURS),
                arguments("20230405000120050", "20230405000120000", ChronoUnit.MINUTES),
                arguments("20230405000120050", "20230405000120000", ChronoUnit.MICROS),
                arguments("20230405000120332", "20230405000120331", ChronoUnit.DAYS),
                arguments("20230405000120332", "20230405000120331", ChronoUnit.MONTHS),
                arguments("20230405000120332", "20230405000120331", ChronoUnit.HOURS),
                arguments("20230405000120332", "20230405000120331", ChronoUnit.MINUTES),
                arguments("20230405000120332", "20230405000120331", ChronoUnit.MICROS),
                arguments("20230405000120332", "20230405000120334", ChronoUnit.DAYS),
                arguments("20230405000120332", "20230405000120334", ChronoUnit.MONTHS),
                arguments("20230405000120332", "20230405000120334", ChronoUnit.HOURS),
                arguments("20230405000120332", "20230405000120334", ChronoUnit.MINUTES),
                arguments("20230405000120332", "20230405000120334", ChronoUnit.MICROS),
                arguments("202304050012300", "202304050014300", ChronoUnit.DAYS),
                arguments("202304050012300", "202304050014300", ChronoUnit.MONTHS),
                arguments("202304050012300", "202304050014300", ChronoUnit.HOURS),
                arguments("202304050012300", "202304050014300", ChronoUnit.MINUTES),
                arguments("202304050012300", "202304050014300", ChronoUnit.MICROS),
                arguments("20230405001230", "20230405001430", ChronoUnit.DAYS),
                arguments("20230405001230", "20230405001430", ChronoUnit.MONTHS),
                arguments("20230405001230", "20230405001430", ChronoUnit.HOURS),
                arguments("20230405001230", "20230405001430", ChronoUnit.MINUTES),
                arguments("20230405001230", "20230405001430", ChronoUnit.MICROS)
//                arguments("202304050012303`32332332`", "20230405001430", 200L)
        );
    }




    @ParameterizedTest(name = "type: {0}, time1: {1}, addTime: {2}")
    @MethodSource("addTime")
    public void addTimeTest(TimeFieldType type, String time1, long addTime) throws Exception {
        TimeStringCal timeStringCal = new TimeStringCal(time1);
        System.out.println("Before Time : \t" + timeStringCal.getTime());
        System.out.println("After Time : \t" + timeStringCal.calculateTime(type, addTime).getTime());

    }

    static Stream<Arguments> addTime() {
        return Stream.of(
                arguments(YEAR, "20230405000122", 2L),
                arguments(MONTH, "20230405000122", -1L),
                arguments(DATE, "20230405000122", 1L),
                arguments(HOUR, "20230405000122", 1L),
                arguments(HOUR, "20230405000122", 41L),
                arguments(MINUTE, "20230405000122", 1L),
                arguments(SECOND, "20230405000122", 1L),
                arguments(MILLI_SECOND, "20230405000122", 1L),
                arguments(MICRO_SECOND, "20230405000122", 1L),
                arguments(NANO_SECOND, "20230405000122", 1L)
        );
    }


    @ParameterizedTest(name = "type: {0}, time1: {1}, time2: {2}, addTime: {3}")
    @MethodSource("addTime2")
    public void CoroutineTest(TimeFieldType type, String time1, String time2, long addTime) {
        TimeStringCal.coroutine(time1, time2, type, addTime, (start) -> System.out.println("-->" +  start.getTime()));
    }

    @Test
    public void CoroutineTest2() {
        TimeStringCal.coroutine("20230405000004", "20230405000004000000100", NANO_SECOND, 1L, (start) -> System.out.println("-->" +  start.getTime()));
    }

    static Stream<Arguments> addTime2() {
        return Stream.of(
                arguments(YEAR, "20230405000122", "20330405000122", 2L),
                arguments(MONTH, "20230405000122", "20240105000122", 1L),
                arguments(MONTH, "20230405000122", "20220305000122", 1L),
                arguments(DATE, "20230405000122", "20230505000122", 1L),
                arguments(HOUR, "20230405000122", "20230407000122", 1L),
                arguments(MINUTE, "20230405000122", "20230405020122", 1L),
                arguments(SECOND, "20230405000122", "20230405000322", 1L),
                arguments(MILLI_SECOND, "20230405000122", "20230405000122300", 1L),
                arguments(MICRO_SECOND, "20230405000122", "202304050001220001", 1L)
//                arguments(NANO_SECOND, "20230405000122", "20230405000122000001", 1L)
        );
    }




    @Test
    public void ttt() {
        System.out.println(fillZero(MILLI_SECOND, "202301010"));
        System.out.println(fillZero(MICRO_SECOND, "202301010"));
        System.out.println(fillZero(NANO_SECOND, "202301010"));
    }

    private String fillZero(final TimeFieldType type, final String _time) {
        String baseTime = _time;
        if (type == MILLI_SECOND || type == MICRO_SECOND || type == NANO_SECOND) {
            if (baseTime.length() >= MILLI_SECOND.getLength() && baseTime.length() >= type.getLength()) {
                return baseTime;
            }
            return baseTime + Base.ZERO.getValue().repeat(Math.abs(type.getLength() - baseTime.length()));
        }
        return baseTime;
    }

    @Test
    public void tesT() {
        String a = "202212";
        String b = a.substring(4, 6);
        System.out.println(b);
        System.out.println(Integer.parseInt(b));


    }




}