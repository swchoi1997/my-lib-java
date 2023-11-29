package org.example.pcap.constants;

import java.nio.ByteOrder;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Endian {
    BIG_ENDIAN("big", "a1b2c3d4", ByteOrder.LITTLE_ENDIAN),
    LITTLE_ENDIAN("little", "d4c3b2a1", ByteOrder.LITTLE_ENDIAN)
    ;

    private final static Map<String, Endian> ENDIAN_MAP =
            Collections.unmodifiableMap(Stream.of(values()).collect(Collectors.toMap(Endian::getValue, Function.identity())));

    private final String name;
    private final String value;

    private final ByteOrder endian;


    Endian(final String name, final String value, final ByteOrder endian) {
        this.name = name;
        this.value = value;
        this.endian = endian;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public ByteOrder getEndian() {
        return endian;
    }

    public static Endian find(final String endian) {
        return Optional
                .ofNullable(ENDIAN_MAP.get(endian))
                .orElseThrow(IllegalArgumentException::new);
    }
}
