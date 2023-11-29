package org.example.pcap.header;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.example.pcap.constants.PacketFrontHeaderType.*;

public class PacketHeader implements Packet {

    private final byte[] time_stamp_second;
    private final byte[] time_stamp_milli;
    private final byte[] packet_length;
    private final byte[] actual_packet_length;

    public PacketHeader(final ByteBuffer buffer) {
        this.time_stamp_second = this.get(TIME_STAMP_SECOND, buffer);
        this.time_stamp_milli = this.get(TIME_STAMP_MILLI, buffer);
        this.packet_length = this.get(PACKET_LENGTH, buffer);
        this.actual_packet_length = this.get(ACTUAL_PACKET_LENGTH, buffer);
    }

    public byte[] getTime_stamp_second() {
        return time_stamp_second;
    }

    public byte[] getTime_stamp_milli() {
        return time_stamp_milli;
    }

    public byte[] getPacket_length() {
        return packet_length;
    }

    public byte[] getActual_packet_length() {
        return actual_packet_length;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacketHeader that = (PacketHeader) o;
        return Arrays.equals(time_stamp_second, that.time_stamp_second) && Arrays.equals(time_stamp_milli, that.time_stamp_milli) && Arrays.equals(packet_length, that.packet_length) && Arrays.equals(actual_packet_length, that.actual_packet_length);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(time_stamp_second);
        result = 31 * result + Arrays.hashCode(time_stamp_milli);
        result = 31 * result + Arrays.hashCode(packet_length);
        result = 31 * result + Arrays.hashCode(actual_packet_length);
        return result;
    }

    @Override
    public String toString() {
        return "PacketFrontHeader{" +
                "time_stamp_second=" + Arrays.toString(time_stamp_second) +
                ", time_stamp_milli=" + Arrays.toString(time_stamp_milli) +
                ", packet_length=" + Arrays.toString(packet_length) +
                ", actual_packet_length=" + Arrays.toString(actual_packet_length) +
                '}';
    }
}

