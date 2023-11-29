package org.example.pcap.constants;

import org.example.pcap.reader.PacketInfo;


public enum GlobalHeaderType implements HeaderType{

    GLOBAL_TOTAL_LENGTH(new PacketInfo(0, 24)),
    MAGIC_NUMBER(new PacketInfo(0, 4)),
    MAJOR_VERSION(new PacketInfo(4, 6)),
    MINOR_VERSION(new PacketInfo(6, 8)),
    TIME_ZONE(new PacketInfo(8, 12)),
    TIME_STAMP(new PacketInfo(12, 16)),
    SNAP_LENGTH(new PacketInfo(16, 20)),
    LINK_TYPE(new PacketInfo(20, 24)),
    ;

    private final PacketInfo packetInfo;

    GlobalHeaderType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;

    }
    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }
}
