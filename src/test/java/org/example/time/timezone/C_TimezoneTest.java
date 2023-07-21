package org.example.time.timezone;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.TimeZone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class C_TimezoneTest {

    @Test
    void NoArgsConstructorTest() {
        C_Timezone timezone = new C_Timezone();
        assertThat(timezone.getTimeZoneStr()).isEqualTo(TimeZone.getDefault().getID());
        assertThat(timezone.getTimeZoneId().toString()).isEqualTo(E_TimeZone.SEOUL.getTimeZoneStr());
    }

    @Test
    void OneArgsConstructorStrTest() {
        String desireTimeZone = "Asia/Tokyo";
        C_Timezone timezone = new C_Timezone(desireTimeZone);
        assertThat(timezone.getTimeZoneStr()).isEqualTo(desireTimeZone);
        assertThat(timezone.getTimeZoneId().toString()).isEqualTo(E_TimeZone.TOKYO.getTimeZoneStr());
    }

    @Test
    void OneArgsConstructorEnumTest() {
        E_TimeZone beijing = E_TimeZone.BEIJING;
        C_Timezone timezone = new C_Timezone(beijing);
        assertThat(timezone.getTimeZoneStr()).isEqualTo(beijing.getTimeZoneStr());
        assertThat(timezone.getTimeZoneId().toString()).isEqualTo(beijing.getTimeZoneStr());
    }

}