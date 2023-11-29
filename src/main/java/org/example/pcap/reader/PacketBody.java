package org.example.pcap.reader;

import org.example.pcap.body.PacketData;
import org.example.pcap.header.PacketBodyHeader;
import org.example.pcap.header.linkType.LinkTypeHeader;
import org.example.pcap.header.protocol.ProtocolPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacketBody {

    private final LinkTypeHeader linkTypeHeader;
    private final PacketBodyHeader packetBodyHeader;
    private final ProtocolPacket protocolPacket;
    private final PacketData packetData;

    public PacketBody(final LinkTypeHeader linkTypeHeader,
                       final PacketBodyHeader packetBodyHeader,
                       final ProtocolPacket protocolPacket,
                       final PacketData packetData) {
        this.linkTypeHeader = linkTypeHeader;
        this.packetBodyHeader = packetBodyHeader;
        this.protocolPacket = protocolPacket;
        this.packetData = packetData;
    }

    public LinkTypeHeader getLinkTypeHeader() {
        return linkTypeHeader;
    }

    public PacketBodyHeader getPacketBodyHeader() {
        return packetBodyHeader;
    }

    public ProtocolPacket getProtocolPacket() {
        return protocolPacket;
    }

    public PacketData getPacketData() {
        return packetData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacketBody that = (PacketBody) o;
        return Objects.equals(linkTypeHeader, that.linkTypeHeader) && Objects.equals(packetBodyHeader, that.packetBodyHeader) && Objects.equals(protocolPacket, that.protocolPacket) && Objects.equals(packetData, that.packetData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkTypeHeader, packetBodyHeader, protocolPacket, packetData);
    }

    @Override
    public String toString() {
        return "PacketBody{" +
                "linkTypeHeader=" + linkTypeHeader +
                ", packetBodyHeader=" + packetBodyHeader +
                ", protocolPacket=" + protocolPacket +
                ", packetData=" + packetData +
                '}';
    }
}
