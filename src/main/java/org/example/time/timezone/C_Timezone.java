package org.example.time.timezone;

import java.time.ZoneId;

public class C_Timezone {

    private final ZoneId timeZoneId;
    private final String timeZoneStr;

    public C_Timezone(ZoneId timeZoneId, String timeZoneStr) {
        this.timeZoneId = timeZoneId;
        this.timeZoneStr = timeZoneStr;
    }

    public ZoneId getTimeZoneId() {
        return timeZoneId;
    }

    public String getTimeZoneStr() {
        return timeZoneStr;
    }
}
