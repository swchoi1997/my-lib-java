package org.example.pcap.header.protocol;

import org.example.pcap.header.Packet;

public abstract class ProtocolPacket implements Packet {

    public abstract ProtocolPacket getProtocolPacket();
}
