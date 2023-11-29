package org.example.pcap.constants.linkType;

import org.example.pcap.reader.PacketInfo;
import org.example.pcap.constants.HeaderType;

public enum LinuxSllV1HeaderType implements HeaderType {
    PACKET_TYPE(new PacketInfo(0, 2)),
    LINK_LAYER_ADDRESS_TYPE(new PacketInfo(2, 4)),
    LINK_LAYER_ADDRESS_LENGTH(new PacketInfo(4, 6)),
    LINK_LAYER_ADDRESS(new PacketInfo(6, 14)),
    PROTOCOL_TYPE(new PacketInfo(14,16))
    ;

    private final PacketInfo packetInfo;

    LinuxSllV1HeaderType(final PacketInfo packetInfo) {
        this.packetInfo = packetInfo;
    }

    @Override
    public PacketInfo getPacketInfo() {
        return packetInfo;
    }
}
