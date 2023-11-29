package org.example.pcap.constants.protocol;

import org.example.pcap.constants.HeaderType;
import org.example.pcap.reader.PacketInfo;

public enum TcpType implements HeaderType {
    SOURCE_PORT(new PacketInfo(0, 2)),
    DESTINATION_PORT(new PacketInfo(2, 4)),
    SEQUENCE_NUMBER(new PacketInfo(4, 8)),
    ACKNOWLEDGMENT_NUMBER(new PacketInfo(8, 12)),
    OFFSET_AND_FLAGS(new PacketInfo(12, 14)),
    WINDOW_SIZE(new PacketInfo(14, 16)),
    CHECK_SUM(new PacketInfo(16, 18)),
    URGENT_POINTER(new PacketInfo(18, 20)),
    ;

    private final PacketInfo packetInfo;

    TcpType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;
    }

    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }


}
