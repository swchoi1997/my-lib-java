package org.example.pcap.body;

import org.example.pcap.header.Packet;

import java.nio.ByteBuffer;

public class PacketData implements Packet{

    private final byte[] data;

    public PacketData(final ByteBuffer buffer) {
        this.data = this.get(buffer.remaining(), buffer);
    }

    public byte[] getData() {
        return data;
    }
}
