package org.example.pcap.constants;

import org.example.pcap.reader.PacketInfo;

public enum PacketBehindHeaderType implements HeaderType{

    BEHIND_TOTAL_LENGTH(new PacketInfo(0, 20)),
    VERSION_AND_HEADER_LENGTH (new PacketInfo(0, 1)),
    DSCP(new PacketInfo(1, 2)),
    TOTAL_LENGTH(new PacketInfo(2, 4)),
    IDENTIFICATION(new PacketInfo(4, 6)),
    FRAGMENT_OFFSET(new PacketInfo(6, 8)),
    TIME_TO_LIVE(new PacketInfo(8, 9)),
    PROTOCOL(new PacketInfo(9, 10)),
    HEADER_CHECKSUM(new PacketInfo(10, 12)),
    SOURCE_ADDRESS(new PacketInfo(12, 16)),
    DESTINATION_ADDRESS(new PacketInfo(16, 20)),
    ;


    private final PacketInfo packetInfo;

    PacketBehindHeaderType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;

    }

    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }
}
