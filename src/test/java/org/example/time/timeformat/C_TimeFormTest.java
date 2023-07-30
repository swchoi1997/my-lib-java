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
    void validTimeFormat1(String time, E_TimeFormStd timeFormat, E_TimeFormStd timeFormat2) {
        C_TimeForm timeForm = new C_TimeForm();
        String convertTime = timeFormat.convertTimeFormat(time, timeFormat);
        assertThat(convertTime.length()).isEqualTo(timeFormat.getLen());
        assertThat(timeForm.isFitTimeFormat(convertTime, timeFormat)).isTrue();
    }

    @ParameterizedTest(name = "time: {0} timeFormat : {1} timeFormat2 : {2}")
    @MethodSource("isValidTimeFormat")
    void validTimeFormat2(String time, E_TimeFormStd timeFormat, E_TimeFormStd timeFormat2) {
        C_TimeForm timeForm = new C_TimeForm();
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
                arguments(time1, E_TimeFormStd.YYYY, E_TimeFormStd.YYYYMM),
                arguments(time2, E_TimeFormStd.YYYYMM, E_TimeFormStd.YYYY),
                arguments(time3, E_TimeFormStd.YYYYMMDD, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time4, E_TimeFormStd.YYYYMMDDHH24, E_TimeFormStd.YYYYMMDD),
                arguments(time5, E_TimeFormStd.YYYYMMDDHH24MI, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time6, E_TimeFormStd.YYYYMMDDHH24MISS, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time7, E_TimeFormStd.YYYYMMDDHH24MISSMILLI, E_TimeFormStd.YYYYMMDDHH24MISS)
        );
    }


    @ParameterizedTest(name = "time: {0} timeFormat : {1}")
    @MethodSource("isValidTimeFormatAll")
    void validTimeFormatAll(String time, E_TimeFormStd timeFormat) {
        C_TimeForm timeForm = new C_TimeForm();
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
                arguments(time1, E_TimeFormStd.YYYY),
                arguments(time2, E_TimeFormStd.YYYY),
                arguments(time3, E_TimeFormStd.YYYY),
                arguments(time4, E_TimeFormStd.YYYY),
                arguments(time5, E_TimeFormStd.YYYY),
                arguments(time6, E_TimeFormStd.YYYY),
                arguments(time7, E_TimeFormStd.YYYY),
                arguments(time8, E_TimeFormStd.YYYY),
                arguments(time9, E_TimeFormStd.YYYY),
                arguments(time10, E_TimeFormStd.YYYY),
                arguments(time11, E_TimeFormStd.YYYY),
                arguments(time12, E_TimeFormStd.YYYY),
                arguments(time1, E_TimeFormStd.YYYYMM),
                arguments(time2, E_TimeFormStd.YYYYMM),
                arguments(time3, E_TimeFormStd.YYYYMM),
                arguments(time4, E_TimeFormStd.YYYYMM),
                arguments(time5, E_TimeFormStd.YYYYMM),
                arguments(time6, E_TimeFormStd.YYYYMM),
                arguments(time7, E_TimeFormStd.YYYYMM),
                arguments(time8, E_TimeFormStd.YYYYMM),
                arguments(time9, E_TimeFormStd.YYYYMM),
                arguments(time10, E_TimeFormStd.YYYYMM),
                arguments(time11, E_TimeFormStd.YYYYMM),
                arguments(time12, E_TimeFormStd.YYYYMM),
                arguments(time1, E_TimeFormStd.YYYYMMDD),
                arguments(time2, E_TimeFormStd.YYYYMMDD),
                arguments(time3, E_TimeFormStd.YYYYMMDD),
                arguments(time4, E_TimeFormStd.YYYYMMDD),
                arguments(time5, E_TimeFormStd.YYYYMMDD),
                arguments(time6, E_TimeFormStd.YYYYMMDD),
                arguments(time7, E_TimeFormStd.YYYYMMDD),
                arguments(time8, E_TimeFormStd.YYYYMMDD),
                arguments(time9, E_TimeFormStd.YYYYMMDD),
                arguments(time10, E_TimeFormStd.YYYYMMDD),
                arguments(time11, E_TimeFormStd.YYYYMMDD),
                arguments(time12, E_TimeFormStd.YYYYMMDD),
                arguments(time1, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time2, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time3, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time4, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time5, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time6, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time7, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time8, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time9, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time10, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time11, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time12, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time1, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time2, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time3, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time4, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time5, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time6, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time7, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time8, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time9, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time10, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time11, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time12, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time1, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time2, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time3, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time4, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time5, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time6, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time7, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time8, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time9, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time10, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time11, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time12, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time1, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time2, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time3, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time4, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time5, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time6, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time7, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time8, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time9, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time10, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time11, E_TimeFormStd.YYYYMMDDHH24MISSMILLI),
                arguments(time12, E_TimeFormStd.YYYYMMDDHH24MISSMILLI)
        );
    }


    @ParameterizedTest(name = "time: {0} timeFormat : {1} timeFormat2 : {2}")
    @MethodSource("isNotValidTimeFormat")
    void notValidTimeFormat(String time, E_TimeFormStd timeFormat, E_TimeFormStd timeFormat2) {
        C_TimeForm timeForm = new C_TimeForm();
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
                arguments(time1, E_TimeFormStd.YYYYMM, E_TimeFormStd.YYYY),
                arguments(time2, E_TimeFormStd.YYYY, E_TimeFormStd.YYYYMM),
                arguments(time3, E_TimeFormStd.YYYYMMDDHH24, E_TimeFormStd.YYYYMMDD),
                arguments(time4, E_TimeFormStd.YYYYMMDD, E_TimeFormStd.YYYYMMDDHH24),
                arguments(time5, E_TimeFormStd.YYYYMMDDHH24MISSMILLI, E_TimeFormStd.YYYYMMDDHH24MI),
                arguments(time6, E_TimeFormStd.YYYYMMDDHH24MISSMILLI, E_TimeFormStd.YYYYMMDDHH24MISS),
                arguments(time7, E_TimeFormStd.YYYYMMDDHH24MISS, E_TimeFormStd.YYYYMMDDHH24MISSMILLI)
        );
    }

    @Test
    void constructTestCount1()
    {
        C_TimeForm timeForm = new C_TimeForm("2021111104");
        Assertions.assertThat(timeForm.getTime().length()).isEqualTo(E_TimeFormStd.YYYYMMDDHH24MISS.getLen());
    }

    @Test
    void constructTestCount2()
    {
        C_TimeForm timeForm = new C_TimeForm("2021111104", E_TimeFormStd.YYYYMMDDHH24MISSMILLI);
        Assertions.assertThat(timeForm.getTime().length()).isEqualTo(E_TimeFormStd.YYYYMMDDHH24MISSMILLI.getLen());
    }

    @Test
    void validTimeFormatPretty()
    {
        String time = "2021111104";
        C_TimeForm timeForm = new C_TimeForm(time);
        String convertedTime = timeForm.convertTimeFormat(E_TimeFormPretty.HOUR_MINUTE);

        System.out.println(convertedTime);


    }


}