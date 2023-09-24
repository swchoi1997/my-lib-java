package org.example.time.timezone;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TimeZoneType {

    SEOUL("Asia/Seoul"),
    TOKYO("Asia/Tokyo"),
    BEIJING("Asia/Shanghai"),
    DELHI("Asia/Kolkata"),
    NEW_YORK("America/New_York"),
    SAO_PAULO("America/Sao_Paulo"),
    LONDON("Europe/London"),
    PARIS("Europe/Paris"),
    MOSCOW("Europe/Moscow"),
    SYDNEY("Australia/Sydney"),
    LOS_ANGELES("America/Los_Angeles"),
    CHICAGO("America/Chicago"),
    BERLIN("Europe/Berlin"),
    ISTANBUL("Europe/Istanbul"),
    DUBAI("Asia/Dubai"),
    MUMBAI("Asia/Mumbai"),
    HONG_KONG("Asia/Hong_Kong"),
    SINGAPORE("Asia/Singapore"),
    AUCKLAND("Pacific/Auckland"),
    JAKARTA("Asia/Jakarta"),
    BANGKOK("Asia/Bangkok"),
    CAIRO("Africa/Cairo"),
    JOHANNESBURG("Africa/Johannesburg"),
    BUENOS_AIRES("America/Argentina/Buenos_Aires"),
    TORONTO("America/Toronto"),
    MEXICO_CITY("America/Mexico_City"),
    ROME("Europe/Rome"),
    STOCKHOLM("Europe/Stockholm"),
    SANTIAGO("America/Santiago"),
    LIMA("America/Lima"),
    DALLAS("America/Dallas"),
    DENVER("America/Denver"),
    MIAMI("America/Miami"),
    BOSTON("America/Boston"),
    PHOENIX("America/Phoenix"),
    VANCOUVER("America/Vancouver"),
    SAIPAN("Pacific/Saipan"),
    WELLINGTON("Pacific/Wellington"),
    NOUMEA("Pacific/Noumea"),
    PERTH("Australia/Perth");

    private static final Map<TimeZoneType, String> timeZone =
            Collections.unmodifiableMap(
                    Stream.of(values()).collect(Collectors.toMap(Function.identity(), TimeZoneType::getTimeZoneStr)));

    private static final Map<String, TimeZoneType> timeZoneId =
            Collections.unmodifiableMap(
                    Stream.of(values()).collect(Collectors.toMap(TimeZoneType::getTimeZoneStr, Function.identity())));
    private final String timeZoneStr;

    TimeZoneType(String timeZoneId) {
        this.timeZoneStr = timeZoneId;
    }

    public String getTimeZoneStr() {
        return timeZoneStr;
    }

    public static String Find(TimeZoneType eTimeZone) {
        final String result = timeZone.get(eTimeZone);
        if (result != null) return result;

        throw new IllegalArgumentException();
    }

    public static TimeZoneType Find(String timeZoneStr) {
        final TimeZoneType result = timeZoneId.get(timeZoneStr);
        if (result != null) return result;

        throw new IllegalArgumentException();
    }
}
