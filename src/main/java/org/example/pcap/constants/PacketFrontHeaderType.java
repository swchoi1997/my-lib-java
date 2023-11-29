package org.example.pcap.constants;

import org.example.pcap.reader.PacketInfo;
public enum PacketFrontHeaderType  implements HeaderType{
    PACKET_FRONT_HEADER_TOTAL_LENGTH(new PacketInfo(0, 16)),
    TIME_STAMP_SECOND (new PacketInfo(0, 4)),
    TIME_STAMP_MILLI(new PacketInfo(4, 8)),
    PACKET_LENGTH(new PacketInfo(8, 12)),
    ACTUAL_PACKET_LENGTH(new PacketInfo(12, 16))
    ;


    private final PacketInfo packetInfo;
    PacketFrontHeaderType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;
    }
    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }
}
