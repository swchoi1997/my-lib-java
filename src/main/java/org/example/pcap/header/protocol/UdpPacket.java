package org.example.pcap.header.protocol;


import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.example.pcap.constants.protocol.UdpType.*;

public class UdpPacket extends ProtocolPacket {
    private final byte[] source_port;
    private final byte[] destination_port;
    private final byte[] length;
    private final byte[] checksum;

    public UdpPacket(final ByteBuffer buffer) {
        this.source_port = this.get(SOURCE_PORT, buffer);
        this.destination_port = this.get(DESTINATION_PORT, buffer);
        this.length = this.get(LENGTH, buffer);
        this.checksum = this.get(CHECKSUM, buffer);
    }

    @Override
    public ProtocolPacket getProtocolPacket() {
        return this;
    }

    public byte[] getSource_port() {
        return source_port;
    }

    public byte[] getDestination_port() {
        return destination_port;
    }

    public byte[] getLength() {
        return length;
    }

    public byte[] getChecksum() {
        return checksum;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UdpPacket udpHeader = (UdpPacket) o;
        return Arrays.equals(source_port, udpHeader.source_port) && Arrays.equals(destination_port, udpHeader.destination_port) && Arrays.equals(length, udpHeader.length) && Arrays.equals(checksum, udpHeader.checksum);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(source_port);
        result = 31 * result + Arrays.hashCode(destination_port);
        result = 31 * result + Arrays.hashCode(length);
        result = 31 * result + Arrays.hashCode(checksum);
        return result;
    }

    @Override
    public String toString() {
        return "UdpHeader{" +
                "source_port=" + Arrays.toString(source_port) +
                ", destination_port=" + Arrays.toString(destination_port) +
                ", length=" + Arrays.toString(length) +
                ", checksum=" + Arrays.toString(checksum) +
                '}';
    }
}
