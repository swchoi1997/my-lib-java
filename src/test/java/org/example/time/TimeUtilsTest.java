package org.example.time;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneId;
import org.example.time.timeformat.TimeFormStd;
import org.example.time.timezone.TimeZoneType;
import org.junit.jupiter.api.Test;

class TimeUtilsTest {

    @Test
    void nowReturnsFormattedTime() {
        ZoneId zone = ZoneId.of(TimeZoneType.SEOUL.getTimeZoneStr());
        String now = TimeUtils.now(TimeFormStd.YYYYMMDDHH24MISS, zone);
        assertThat(now.length()).isEqualTo(TimeFormStd.YYYYMMDDHH24MISS.getLen());
    }

    @Test
    void epochRoundTrip() {
        String time = "20240101010101";
        ZoneId zone = ZoneId.of(TimeZoneType.SEOUL.getTimeZoneStr());
        long epoch = TimeUtils.toEpochMillis(time, TimeFormStd.YYYYMMDDHH24MISS, zone);
        String back = TimeUtils.fromEpochMillis(epoch, TimeFormStd.YYYYMMDDHH24MISS, zone);
        assertThat(back).isEqualTo(time);
    }

    @Test
    void convertBetweenZones() {
        String seoulTime = "20240101080000";
        ZoneId seoul = ZoneId.of(TimeZoneType.SEOUL.getTimeZoneStr());
        long epoch = TimeUtils.toEpochMillis(seoulTime, TimeFormStd.YYYYMMDDHH24MISS, seoul);

        ZoneId london = ZoneId.of(TimeZoneType.LONDON.getTimeZoneStr());
        String londonTime = TimeUtils.fromEpochMillis(epoch, TimeFormStd.YYYYMMDDHH24MISS, london);
        long epochAgain = TimeUtils.toEpochMillis(londonTime, TimeFormStd.YYYYMMDDHH24MISS, london);
        assertThat(epochAgain).isEqualTo(epoch);
        assertThat(londonTime).isNotEqualTo(seoulTime);
    }
}
