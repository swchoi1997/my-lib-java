package org.example.time.timezone;

import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Wrapper around a {@link ZoneId} providing convenient constructors for common
 * {@link TimeZoneType} values.
 */

public class Timezone {

    private final ZoneId timeZoneId;
    private final String timeZoneStr;

    /**
     * Creates an instance using the JVM default time zone.
     */
    public Timezone() {
        this(TimeZone.getDefault().getID());
    }

    /**
     * Creates an instance from a zone id string.
     */
    public Timezone(final String desireTimeZone) {
        this(TimeZoneType.Find(desireTimeZone));
    }


    /**
     * Creates an instance from a {@link TimeZoneType} value.
     */
    public Timezone(final TimeZoneType desireTimeZone) {
        this(ZoneId.of(desireTimeZone.getTimeZoneStr()), TimeZoneType.Find(desireTimeZone));
    }

    private Timezone(ZoneId timeZoneId, String timeZoneStr) {
        this.timeZoneId = timeZoneId;
        this.timeZoneStr = timeZoneStr;
    }

    /**
     * Returns the resolved {@link ZoneId}.
     */
    public ZoneId getTimeZoneId() {
        return timeZoneId;
    }

    /**
     * Returns the string representation of the time zone.
     */
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
