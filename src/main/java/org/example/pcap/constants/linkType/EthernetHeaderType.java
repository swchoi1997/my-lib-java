package org.example.pcap.constants.linkType;

import org.example.pcap.reader.PacketInfo;
import org.example.pcap.constants.HeaderType;


public enum EthernetHeaderType implements HeaderType {
    DESTINATION(new PacketInfo(0, 6)),
    SOURCE(new PacketInfo(6, 12)),
    PROTOCOL_TYPE(new PacketInfo(12, 14)),
    ;

    private final PacketInfo packetInfo;

    EthernetHeaderType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;

    }

    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }
}
