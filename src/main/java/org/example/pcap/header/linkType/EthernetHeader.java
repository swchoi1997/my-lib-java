package org.example.pcap.header.linkType;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.example.pcap.constants.linkType.EthernetHeaderType.*;

public class EthernetHeader extends LinkTypeHeader {
    private final byte[] destination;
    private final byte[] source;
    private final byte[] protocol_type;

    public EthernetHeader(final ByteBuffer buffer) {
        this.destination = this.get(DESTINATION, buffer);
        this.source = this.get(SOURCE, buffer);
        this.protocol_type = this.get(PROTOCOL_TYPE, buffer);
    }

    public byte[] getDestination() {
        return destination;
    }

    public byte[] getSource() {
        return source;
    }

    public byte[] getProtocol_type() {
        return protocol_type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EthernetHeader that = (EthernetHeader) o;
        return Arrays.equals(destination, that.destination) && Arrays.equals(source, that.source) && Arrays.equals(protocol_type, that.protocol_type);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(destination);
        result = 31 * result + Arrays.hashCode(source);
        result = 31 * result + Arrays.hashCode(protocol_type);
        return result;
    }

    @Override
    public String toString() {
        return "EthernetHeader{" +
                "destination=" + Arrays.toString(destination) +
                ", source=" + Arrays.toString(source) +
                ", protocol_type=" + Arrays.toString(protocol_type) +
                '}';
    }
}
