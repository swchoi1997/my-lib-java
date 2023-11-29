package org.example.pcap.constants.protocol;

import org.example.pcap.constants.HeaderType;
import org.example.pcap.reader.PacketInfo;

public enum UdpType implements HeaderType {
    SOURCE_PORT(new PacketInfo(0, 2)),
    DESTINATION_PORT(new PacketInfo(2, 4)),
    LENGTH(new PacketInfo(4, 6)),
    CHECKSUM(new PacketInfo(6, 8)),
    ;

    private final PacketInfo packetInfo;

    UdpType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;

    }

    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }
}
