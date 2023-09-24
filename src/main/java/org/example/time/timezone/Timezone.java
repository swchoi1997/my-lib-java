package org.example.time.timezone;

import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

public class Timezone {

    private final ZoneId timeZoneId;
    private final String timeZoneStr;

    public Timezone() {
        this(TimeZone.getDefault().getID());
    }

    public Timezone(final String desireTimeZone) {
        this(TimeZoneType.Find(desireTimeZone));
    }


    public Timezone(final TimeZoneType desireTimeZone) {
        this(ZoneId.of(desireTimeZone.getTimeZoneStr()), TimeZoneType.Find(desireTimeZone));
    }

    private Timezone(ZoneId timeZoneId, String timeZoneStr) {
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
        Timezone that = (Timezone) o;
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
