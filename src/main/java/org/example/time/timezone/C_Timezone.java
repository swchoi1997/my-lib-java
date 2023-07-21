package org.example.time.timezone;

import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

public class C_Timezone {

    private final ZoneId timeZoneId;
    private final String timeZoneStr;

    public C_Timezone() {
        this(TimeZone.getDefault().getID());
    }

    public C_Timezone(final String desireTimeZone) {
        this(E_TimeZone.Find(desireTimeZone));
    }


    public C_Timezone(final E_TimeZone desireTimeZone) {
        this(ZoneId.of(desireTimeZone.getTimeZoneStr()), E_TimeZone.Find(desireTimeZone));
    }

    private C_Timezone(ZoneId timeZoneId, String timeZoneStr) {
        this.timeZoneId = timeZoneId;
        this.timeZoneStr = timeZoneStr;
    }

    public ZoneId getTimeZoneId() {
        return timeZoneId;
    }

    public String getTimeZoneStr() {
        return timeZoneStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        C_Timezone that = (C_Timezone) o;
        return Objects.equals(timeZoneId, that.timeZoneId) && Objects.equals(timeZoneStr,
                that.timeZoneStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeZoneId, timeZoneStr);
    }

    @Override
    public String toString() {
        return "C_Timezone{" +
                "timeZoneId=" + timeZoneId +
                ", timeZoneStr='" + timeZoneStr + '\'' +
                '}';
    }
}
