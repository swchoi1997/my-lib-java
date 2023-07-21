package org.example.time.timezone;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum E_TimeZone{

    SEOUL("Asia/Seoul"),
    NEW_YORK("America/New_York"),
    LONDON("Europe/London"),
    TOKYO("Asia/Tokyo"),
    PARIS("Europe/Paris"),
    BEIJING("Asia/Shanghai"),
    SYDNEY("Australia/Sydney"),
    SAO_PAULO("America/Sao_Paulo"),
    MOSCOW("Europe/Moscow"),
    DELHI("Asia/Kolkata");

    private static final Map<E_TimeZone, String> timezone =
            Collections.unmodifiableMap(
                    Stream.of(values()).collect(Collectors.toMap(Function.identity(), E_TimeZone::getTimeZoneId)));
    private final String timeZoneId;

    E_TimeZone(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public static String Find(E_TimeZone eTimeZone) {
        return timezone.get(eTimeZone);
    }
}
