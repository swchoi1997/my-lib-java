package org.example.pcap.header;

import org.example.pcap.reader.PacketInfo;
import org.example.pcap.constants.HeaderType;

import java.nio.ByteBuffer;

public interface Packet {

    default byte[] get(final int length, final ByteBuffer byteBuffer) {
        if (length == 0) {
            return new byte[0];
        }
        byte[] bytes = new byte[length];
        byteBuffer.get(bytes);

        return bytes;
    }

    default byte[] get(final HeaderType headerType, final ByteBuffer byteBuffer) {
        return this.get(headerType.getPacketInfo().getLength(), byteBuffer);

    }
}
