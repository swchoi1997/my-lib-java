package org.example.pcap.header.protocol;

import org.example.base.Base;
import org.example.pcap.constants.protocol.TcpType;
import org.example.pcap.header.Packet;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Optional;

import static org.example.base.Base.*;
import static org.example.utils.ByteUtils.*;

public class TcpPacket extends ProtocolPacket {

    private static final int DEFAULT_TCP_LENGTH = 20;
    private final byte[] source_port;
    private final byte[] destination_port;
    private final byte[] sequence_number;
    private final byte[] acknowledgment_number;
    private final byte[] offset_and_flags;
    private final byte[] window_size;
    private final byte[] check_sum;
    private final byte[] urgent_pointer;
    private final byte[] etc;

    public TcpPacket(final ByteBuffer buffer) {
        this.source_port = this.get(TcpType.SOURCE_PORT, buffer);
        this.destination_port = this.get(TcpType.DESTINATION_PORT, buffer);
        this.sequence_number = this.get(TcpType.SEQUENCE_NUMBER, buffer);
        this.acknowledgment_number = this.get(TcpType.ACKNOWLEDGMENT_NUMBER, buffer);
        this.offset_and_flags = this.get(TcpType.OFFSET_AND_FLAGS, buffer);
        this.window_size = this.get(TcpType.WINDOW_SIZE, buffer);
        this.check_sum = this.get(TcpType.CHECK_SUM, buffer);
        this.urgent_pointer = this.get(TcpType.URGENT_POINTER, buffer);
        this.etc = this.get(this.checkHeaderLength() - DEFAULT_TCP_LENGTH, buffer);
    }

    @Override
    public ProtocolPacket getProtocolPacket() {
        return this;
    }

    private Integer checkHeaderLength() {
        return Integer.parseInt(BYTE_LENGTH.getValue()) * Integer.parseUnsignedInt(
                toStr(toStringArrayReverseEndian(this.getDataOffset())), 16);
    }

    public byte[] getDataOffset() {
        byte[] offset = new byte[1];
        offset[0] = (byte) ((this.offset_and_flags[0] >> 4) & 0x0F);
        return offset;
    }

    public byte[] getReserved() {
        byte[] reserved = new byte[1];
        reserved[0] = (byte) ((this.offset_and_flags[0] & 0x1C) >> 2);
        return reserved;
    }

    public byte[] getFlags() {
        int flags = ((this.offset_and_flags[1] << 8) | (this.offset_and_flags[2] & 0xFF)) & 0x1FF;

        byte[] flagBytes = new byte[2];
        flagBytes[0] = (byte) ((flags >> 8) & 0x01); // 상위 바이트
        flagBytes[1] = (byte) (flags & 0xFF);       // 하위 바이트

        return flagBytes;
    }

    public byte[] getSource_port() {
        return source_port;
    }

    public byte[] getDestination_port() {
        return destination_port;
    }

    public byte[] getSequence_number() {
        return sequence_number;
    }

    public byte[] getAcknowledgment_number() {
        return acknowledgment_number;
    }

    public byte[] getOffset_and_flags() {
        return offset_and_flags;
    }

    public byte[] getWindow_size() {
        return window_size;
    }

    public byte[] getCheck_sum() {
        return check_sum;
    }

    public byte[] getUrgent_pointer() {
        return urgent_pointer;
    }

    public byte[] getEtc() {
        return etc;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TcpPacket tcpPacket = (TcpPacket) o;
        return Arrays.equals(source_port, tcpPacket.source_port) && Arrays.equals(destination_port, tcpPacket.destination_port) && Arrays.equals(sequence_number, tcpPacket.sequence_number) && Arrays.equals(acknowledgment_number, tcpPacket.acknowledgment_number) && Arrays.equals(offset_and_flags, tcpPacket.offset_and_flags) && Arrays.equals(window_size, tcpPacket.window_size) && Arrays.equals(check_sum, tcpPacket.check_sum) && Arrays.equals(urgent_pointer, tcpPacket.urgent_pointer) && Arrays.equals(etc, tcpPacket.etc);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(source_port);
        result = 31 * result + Arrays.hashCode(destination_port);
        result = 31 * result + Arrays.hashCode(sequence_number);
        result = 31 * result + Arrays.hashCode(acknowledgment_number);
        result = 31 * result + Arrays.hashCode(offset_and_flags);
        result = 31 * result + Arrays.hashCode(window_size);
        result = 31 * result + Arrays.hashCode(check_sum);
        result = 31 * result + Arrays.hashCode(urgent_pointer);
        result = 31 * result + Arrays.hashCode(etc);
        return result;
    }

    @Override
    public String toString() {
        return "TcpPacket{" +
                "source_port=" + Arrays.toString(toStringArray(source_port)) +
                ", destination_port=" + Arrays.toString(toStringArray(destination_port)) +
                ", sequence_number=" + Arrays.toString(toStringArray(sequence_number)) +
                ", acknowledgment_number=" + Arrays.toString(toStringArray(acknowledgment_number)) +
                ", offset_and_flags=" + Arrays.toString(toStringArray(offset_and_flags)) +
                ", window_size=" + Arrays.toString(toStringArray(window_size)) +
                ", check_sum=" + Arrays.toString(toStringArray(check_sum)) +
                ", urgent_pointer=" + Arrays.toString(toStringArray(urgent_pointer)) +
                ", etc=" + Arrays.toString(toStringArray(etc)) +
                '}';
    }
}
