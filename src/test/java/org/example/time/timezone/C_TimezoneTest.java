package org.example.time.timezone;

import static org.assertj.core.api.Assertions.*;

import java.util.TimeZone;
import org.junit.jupiter.api.Test;

class C_TimezoneTest {

    @Test
    void NoArgsConstructorTest() {
        Timezone timezone = new Timezone();
        assertThat(timezone.getTimeZoneStr()).isEqualTo(TimeZone.getDefault().getID());
        assertThat(timezone.getTimeZoneId().toString()).isEqualTo(TimeZoneType.SEOUL.getTimeZoneStr());
    }

    @Test
    void OneArgsConstructorStrTest() {
        String desireTimeZone = "Asia/Tokyo";
        Timezone timezone = new Timezone(desireTimeZone);
        assertThat(timezone.getTimeZoneStr()).isEqualTo(desireTimeZone);
        assertThat(timezone.getTimeZoneId().toString()).isEqualTo(TimeZoneType.TOKYO.getTimeZoneStr());
    }

    @Test
    void OneArgsConstructorEnumTest() {
        TimeZoneType beijing = TimeZoneType.BEIJING;
        Timezone timezone = new Timezone(beijing);
        assertThat(timezone.getTimeZoneStr()).isEqualTo(beijing.getTimeZoneStr());
        assertThat(timezone.getTimeZoneId().toString()).isEqualTo(beijing.getTimeZoneStr());
    }

}