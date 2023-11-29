package org.example.pcap.constants.linkType;

import org.example.pcap.reader.PacketInfo;
import org.example.pcap.constants.HeaderType;

public enum LinuxSllV2HeaderType implements HeaderType {
    PROTOCOL_TYPE(new PacketInfo(0, 2)),
    RESERVED(new PacketInfo(2, 4)),
    INTERFACE_INDEX(new PacketInfo(4, 8)),
    LINK_LAYER_ADDRESS_TYPE(new PacketInfo(8, 10)),
    PACKET_TYPE(new PacketInfo(10, 11)),
    LINK_LAYER_ADDRESS_LENGTH(new PacketInfo(11, 12)),
    LINK_LAYER_ADDRESS(new PacketInfo(12,20))
    ;

    private final PacketInfo packetInfo;

    LinuxSllV2HeaderType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;
    }

    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }
}
