package org.example.pcap.header;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.example.pcap.constants.PacketBehindHeaderType.*;
import static org.example.utils.ByteUtils.*;

public class PacketBodyHeader implements Packet {

    private final byte[] version_and_header_length;
    private final byte[] dscp;
    private final byte[] total_length;
    private final byte[] identification;
    private final byte[] fragment_offset;
    private final byte[] time_to_live;
    private final byte[] protocol;
    private final byte[] header_checksum;
    private final byte[] source_address;
    private final byte[] destination_address;

    public PacketBodyHeader(final ByteBuffer buffer) {
        this.version_and_header_length = this.get(VERSION_AND_HEADER_LENGTH, buffer);
        this.dscp = this.get(DSCP, buffer);
        this.total_length = this.get(TOTAL_LENGTH, buffer);
        this.identification = this.get(IDENTIFICATION, buffer);
        this.fragment_offset = this.get(FRAGMENT_OFFSET, buffer);
        this.time_to_live = this.get(TIME_TO_LIVE, buffer);
        this.protocol = this.get(PROTOCOL, buffer);
        this.header_checksum = this.get(HEADER_CHECKSUM, buffer);
        this.source_address = this.get(SOURCE_ADDRESS, buffer);
        this.destination_address = this.get(DESTINATION_ADDRESS, buffer);
    }

    public byte[] getVersion_and_header_length() {
        return version_and_header_length;
    }

    public byte[] getDscp() {
        return dscp;
    }

    public byte[] getTotal_length() {
        return total_length;
    }

    public byte[] getIdentification() {
        return identification;
    }

    public byte[] getFragment_offset() {
        return fragment_offset;
    }

    public byte[] getTime_to_live() {
        return time_to_live;
    }

    public byte[] getProtocol() {
        return protocol;
    }

    public byte[] getHeader_checksum() {
        return header_checksum;
    }

    public byte[] getSource_address() {
        return source_address;
    }

    public byte[] getDestination_address() {
        return destination_address;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacketBodyHeader that = (PacketBodyHeader) o;
        return Arrays.equals(version_and_header_length, that.version_and_header_length) && Arrays.equals(dscp, that.dscp) && Arrays.equals(total_length, that.total_length) && Arrays.equals(identification, that.identification) && Arrays.equals(fragment_offset, that.fragment_offset) && Arrays.equals(time_to_live, that.time_to_live) && Arrays.equals(protocol, that.protocol) && Arrays.equals(header_checksum, that.header_checksum) && Arrays.equals(source_address, that.source_address) && Arrays.equals(destination_address, that.destination_address);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(version_and_header_length);
        result = 31 * result + Arrays.hashCode(dscp);
        result = 31 * result + Arrays.hashCode(total_length);
        result = 31 * result + Arrays.hashCode(identification);
        result = 31 * result + Arrays.hashCode(fragment_offset);
        result = 31 * result + Arrays.hashCode(time_to_live);
        result = 31 * result + Arrays.hashCode(protocol);
        result = 31 * result + Arrays.hashCode(header_checksum);
        result = 31 * result + Arrays.hashCode(source_address);
        result = 31 * result + Arrays.hashCode(destination_address);
        return result;
    }

    @Override
    public String toString() {
        return "PacketBehindHeader{" +
                "version_and_header_length=" + Arrays.toString(toStringArray(version_and_header_length)) +
                ", dscp=" + Arrays.toString(toStringArray(dscp)) +
                ", total_length=" + Arrays.toString(toStringArray(total_length)) +
                ", identification=" +  Arrays.toString(toStringArray(identification))+
                ", -> " + Integer.parseUnsignedInt(toStr(toStringArray(identification)), 16) +
                ", fragment_offset=" + Arrays.toString(toStringArray(fragment_offset)) +
                ", time_to_live=" + Arrays.toString(toStringArray(time_to_live)) +
                ", protocol=" + Arrays.toString(toStringArray(protocol)) +
                ", header_checksum=" + Arrays.toString(toStringArray(header_checksum)) +
                ", source_address=" + Arrays.toString(toStringArray(source_address)) +
                ", destination_address=" + Arrays.toString(toStringArray(destination_address)) +
                '}';
    }
}
