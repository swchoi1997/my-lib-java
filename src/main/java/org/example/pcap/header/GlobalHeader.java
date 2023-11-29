package org.example.pcap.header;

import org.example.base.Base;
import org.example.pcap.constants.Endian;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

import static org.example.pcap.constants.GlobalHeaderType.*;
import static org.example.utils.ByteUtils.*;

public class GlobalHeader implements Packet {

    private Endian endian;
    private final byte[] magic_number;
    private final byte[] major_version;
    private final byte[] minor_version;
    private final byte[] time_zone;
    private final byte[] time_stamp;
    private final byte[] snap_length;
    private final byte[] link_type;

    public GlobalHeader(final ByteBuffer globalHeaderBuffer) {
        this.magic_number = this.get(MAGIC_NUMBER, globalHeaderBuffer);
        this.endian = this.getEndian(this.magic_number);
        globalHeaderBuffer.order(this.endian.getEndian());

        this.major_version = this.get(MAJOR_VERSION, globalHeaderBuffer);
        this.minor_version = this.get(MINOR_VERSION, globalHeaderBuffer);
        this.time_zone = this.get(TIME_ZONE, globalHeaderBuffer);
        this.time_stamp = this.get(TIME_STAMP, globalHeaderBuffer);
        this.snap_length = this.get(SNAP_LENGTH, globalHeaderBuffer);
        this.link_type = this.get(LINK_TYPE, globalHeaderBuffer);
    }

    private Endian getEndian(final byte[] magic_number) {
        return Endian.find(
                String.join(Base.NONE.getValue(), toStringArray(magic_number)));
    }

    public Endian getEndian() {
        return endian;
    }

    public byte[] getMagic_number() {
        return magic_number;
    }

    public byte[] getMajor_version() {
        return major_version;
    }

    public byte[] getMinor_version() {
        return minor_version;
    }

    public byte[] getTime_zone() {
        return time_zone;
    }

    public byte[] getTime_stamp() {
        return time_stamp;
    }

    public byte[] getSnap_length() {
        return snap_length;
    }

    public byte[] getLink_type() {
        return link_type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlobalHeader that = (GlobalHeader) o;
        return endian == that.endian && Arrays.equals(magic_number, that.magic_number) && Arrays.equals(major_version, that.major_version) && Arrays.equals(minor_version, that.minor_version) && Arrays.equals(time_zone, that.time_zone) && Arrays.equals(time_stamp, that.time_stamp) && Arrays.equals(snap_length, that.snap_length) && Arrays.equals(link_type, that.link_type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(endian);
        result = 31 * result + Arrays.hashCode(magic_number);
        result = 31 * result + Arrays.hashCode(major_version);
        result = 31 * result + Arrays.hashCode(minor_version);
        result = 31 * result + Arrays.hashCode(time_zone);
        result = 31 * result + Arrays.hashCode(time_stamp);
        result = 31 * result + Arrays.hashCode(snap_length);
        result = 31 * result + Arrays.hashCode(link_type);
        return result;
    }

    @Override
    public String toString() {
        return "GlobalHeader{" +
                "endian=" + endian +
                ", magic_number=" + String.join(Base.NONE.getValue(), toStringArray(magic_number)) +
                ", major_version=" + Arrays.toString(toStringArray(major_version)) +
                ", minor_version=" + Arrays.toString(toStringArray(minor_version)) +
                ", time_zone=" + Arrays.toString(toStringArray(time_zone)) +
                ", time_stamp=" + Arrays.toString(toStringArray(time_stamp)) +
                ", snap_length=" + Arrays.toString(toStringArray(snap_length)) +
                ", link_type=" + Arrays.toString(toStringArray(link_type)) +
                '}';
    }
}
