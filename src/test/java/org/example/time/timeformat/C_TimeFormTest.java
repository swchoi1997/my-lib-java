package org.example.time.timeformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class C_TimeFormTest {

    @ParameterizedTest(name = "time: {0} timeFormat : {1} timeFormat2 : {2}")
    @MethodSource("isValidTimeFormat")
    void validTimeFormat1(String time, TimeFormStd timeFormat, TimeFormStd timeFormat2) {
        TimeForm timeForm = new TimeForm();
        String convertTime = timeFormat.convertTimeFormat(time, timeFormat);
        assertThat(convertTime.length()).isEqualTo(timeFormat.getLen());
        assertThat(timeForm.isFitTimeFormat(convertTime, timeFormat)).isTrue();
    }

    @ParameterizedTest(name = "time: {0} timeFormat : {1} timeFormat2 : {2}")
    @MethodSource("isValidTimeFormat")
    void validTimeFormat2(String time, TimeFormStd timeFormat, TimeFormStd timeFormat2) {
        TimeForm timeForm = new TimeForm();
        String convertTime = timeFormat2.convertTimeFormat(time, timeFormat2);
        assertThat(convertTime.length()).isEqualTo(timeFormat2.getLen());
        assertThat(timeForm.isFitTimeFormat(convertTime, timeFormat2)).isTrue();
    }

    static Stream<Arguments> isValidTimeFormat() {
        String time1 = "2023";
        String time2 = "202304";
        String time3 = "20230405";
        String time4 = "2023040501";
        String time5 = "202304050320";
        String time6 = "20230405000120";
        String time7 = "20230405000120332";
        return Stream.of(
                arguments(time1, TimeFormStd.YYYY, TimeFormStd.YYYYMM),
                arguments(time2, TimeFormStd.YYYYMM, TimeFormStd.YYYY),
                arguments(time3, TimeFormStd.YYYYMMDD, TimeFormStd.YYYYMMDDHH24),
                arguments(time4, TimeFormStd.YYYYMMDDHH24, TimeFormStd.YYYYMMDD),
                arguments(time5, TimeFormStd.YYYYMMDDHH24MI, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time6, TimeFormStd.YYYYMMDDHH24MISS, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time7, TimeFormStd.YYYYMMDDHH24MISSMILLI, TimeFormStd.YYYYMMDDHH24MISS)
        );
    }


    @ParameterizedTest(name = "time: {0} timeFormat : {1}")
    @MethodSource("isValidTimeFormatAll")
    void validTimeFormatAll(String time, TimeFormStd timeFormat) {
        TimeForm timeForm = new TimeForm();
        String convertTime = timeFormat.convertTimeFormat(time, timeFormat);
        assertThat(convertTime.length()).isEqualTo(timeFormat.getLen());
        assertThat(timeForm.isFitTimeFormat(convertTime, timeFormat)).isTrue();
    }

    static Stream<Arguments> isValidTimeFormatAll() {
        String time1 = "2023";
        String time2 = "202304";
        String time3 = "20230405";
        String time4 = "2023040501";
        String time5 = "202304050320";
        String time6 = "20230405000120";
        String time7 = "20230405000120332";
        String time8 = "20230";
        String time9 = "2023040";
        String time10 = "202304050";
        String time11 = "20230405001";
        String time12 = "2023040500123";
        return Stream.of(
                arguments(time1, TimeFormStd.YYYY),
                arguments(time2, TimeFormStd.YYYY),
                arguments(time3, TimeFormStd.YYYY),
                arguments(time4, TimeFormStd.YYYY),
                arguments(time5, TimeFormStd.YYYY),
                arguments(time6, TimeFormStd.YYYY),
                arguments(time7, TimeFormStd.YYYY),
                arguments(time8, TimeFormStd.YYYY),
                arguments(time9, TimeFormStd.YYYY),
                arguments(time10, TimeFormStd.YYYY),
                arguments(time11, TimeFormStd.YYYY),
                arguments(time12, TimeFormStd.YYYY),
                arguments(time1, TimeFormStd.YYYYMM),
                arguments(time2, TimeFormStd.YYYYMM),
                arguments(time3, TimeFormStd.YYYYMM),
                arguments(time4, TimeFormStd.YYYYMM),
                arguments(time5, TimeFormStd.YYYYMM),
                arguments(time6, TimeFormStd.YYYYMM),
                arguments(time7, TimeFormStd.YYYYMM),
                arguments(time8, TimeFormStd.YYYYMM),
                arguments(time9, TimeFormStd.YYYYMM),
                arguments(time10, TimeFormStd.YYYYMM),
                arguments(time11, TimeFormStd.YYYYMM),
                arguments(time12, TimeFormStd.YYYYMM),
                arguments(time1, TimeFormStd.YYYYMMDD),
                arguments(time2, TimeFormStd.YYYYMMDD),
                arguments(time3, TimeFormStd.YYYYMMDD),
                arguments(time4, TimeFormStd.YYYYMMDD),
                arguments(time5, TimeFormStd.YYYYMMDD),
                arguments(time6, TimeFormStd.YYYYMMDD),
                arguments(time7, TimeFormStd.YYYYMMDD),
                arguments(time8, TimeFormStd.YYYYMMDD),
                arguments(time9, TimeFormStd.YYYYMMDD),
                arguments(time10, TimeFormStd.YYYYMMDD),
                arguments(time11, TimeFormStd.YYYYMMDD),
                arguments(time12, TimeFormStd.YYYYMMDD),
                arguments(time1, TimeFormStd.YYYYMMDDHH24),
                arguments(time2, TimeFormStd.YYYYMMDDHH24),
                arguments(time3, TimeFormStd.YYYYMMDDHH24),
                arguments(time4, TimeFormStd.YYYYMMDDHH24),
                arguments(time5, TimeFormStd.YYYYMMDDHH24),
                arguments(time6, TimeFormStd.YYYYMMDDHH24),
                arguments(time7, TimeFormStd.YYYYMMDDHH24),
                arguments(time8, TimeFormStd.YYYYMMDDHH24),
                arguments(time9, TimeFormStd.YYYYMMDDHH24),
                arguments(time10, TimeFormStd.YYYYMMDDHH24),
                arguments(time11, TimeFormStd.YYYYMMDDHH24),
                arguments(time12, TimeFormStd.YYYYMMDDHH24),
                arguments(time1, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time2, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time3, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time4, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time5, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time6, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time7, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time8, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time9, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time10, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time11, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time12, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time1, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time2, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time3, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time4, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time5, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time6, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time7, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time8, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time9, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time10, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time11, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time12, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time1, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time2, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time3, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time4, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time5, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time6, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time7, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time8, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time9, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time10, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time11, TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time12, TimeFormStd.YYYYMMDDHH24MISSMILLI)
        );
    }


    @ParameterizedTest(name = "time: {0} timeFormat : {1} timeFormat2 : {2}")
    @MethodSource("isNotValidTimeFormat")
    void notValidTimeFormat(String time, TimeFormStd timeFormat, TimeFormStd timeFormat2) {
        TimeForm timeForm = new TimeForm();
        String convertTime = timeFormat.convertTimeFormat(time, timeFormat);
        assertThat(convertTime.length()).isNotEqualTo(timeFormat2.getLen());
        assertThat(timeForm.isFitTimeFormat(convertTime, timeFormat2)).isFalse();
    }

    static Stream<Arguments> isNotValidTimeFormat() {
        String time1 = "2023";
        String time2 = "202304";
        String time3 = "20230405";
        String time4 = "2023040501";
        String time5 = "202304050320";
        String time6 = "20230405000120";
        String time7 = "20230405000120332";
        return Stream.of(
                arguments(time1, TimeFormStd.YYYYMM, TimeFormStd.YYYY),
                arguments(time2, TimeFormStd.YYYY, TimeFormStd.YYYYMM),
                arguments(time3, TimeFormStd.YYYYMMDDHH24, TimeFormStd.YYYYMMDD),
                arguments(time4, TimeFormStd.YYYYMMDD, TimeFormStd.YYYYMMDDHH24),
                arguments(time5, TimeFormStd.YYYYMMDDHH24MISSMILLI, TimeFormStd.YYYYMMDDHH24MI),
                arguments(time6, TimeFormStd.YYYYMMDDHH24MISSMILLI, TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time7, TimeFormStd.YYYYMMDDHH24MISS, TimeFormStd.YYYYMMDDHH24MISSMILLI)
        );
    }

    @Test
    void constructTestCount1()
    {
        TimeForm timeForm = new TimeForm("2021111104");
        Assertions.assertThat(timeForm.getTime().length()).isEqualTo(TimeFormStd.YYYYMMDDHH24MISS.getLen());
    }

    @Test
    void constructTestCount2()
    {
        TimeForm timeForm = new TimeForm("2021111104", TimeFormStd.YYYYMMDDHH24MISSMILLI);
        Assertions.assertThat(timeForm.getTime().length()).isEqualTo(TimeFormStd.YYYYMMDDHH24MISSMILLI.getLen());
    }

    @Test
    void validTimeFormatPretty()
    {
        String time = "2021111104";
        TimeForm timeForm = new TimeForm(time);
        String convertedTime = timeForm.convertTimeFormat(TimeFormPretty.HOUR_MINUTE);

        System.out.println(convertedTime);


    }


}