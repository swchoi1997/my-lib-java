package org.example.pcap.header.linkType;

import org.example.pcap.constants.linkType.LinuxSllV1HeaderType;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class LinuxSllV1Header extends LinkTypeHeader {
    private final byte[] packet_type;
    private final byte[] link_layer_address_type;
    private final byte[] link_layer_address_length;
    private final byte[] link_layer_address;
    private final byte[] protocol_type;

    public LinuxSllV1Header(final ByteBuffer buffer) {
        this.packet_type = this.get(LinuxSllV1HeaderType.PACKET_TYPE, buffer);
        this.link_layer_address_type = this.get(LinuxSllV1HeaderType.LINK_LAYER_ADDRESS_TYPE, buffer);
        this.link_layer_address_length = this.get(LinuxSllV1HeaderType.LINK_LAYER_ADDRESS_LENGTH, buffer);
        this.link_layer_address = this.get(LinuxSllV1HeaderType.LINK_LAYER_ADDRESS, buffer);
        this.protocol_type = this.get(LinuxSllV1HeaderType.PROTOCOL_TYPE, buffer);
    }

    public byte[] getPacket_type() {
        return packet_type;
    }

    public byte[] getLink_layer_address_type() {
        return link_layer_address_type;
    }

    public byte[] getLink_layer_address_length() {
        return link_layer_address_length;
    }

    public byte[] getLink_layer_address() {
        return link_layer_address;
    }

    public byte[] getProtocol_type() {
        return protocol_type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinuxSllV1Header that = (LinuxSllV1Header) o;
        return Arrays.equals(packet_type, that.packet_type) && Arrays.equals(link_layer_address_type, that.link_layer_address_type) && Arrays.equals(link_layer_address_length, that.link_layer_address_length) && Arrays.equals(link_layer_address, that.link_layer_address) && Arrays.equals(protocol_type, that.protocol_type);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(packet_type);
        result = 31 * result + Arrays.hashCode(link_layer_address_type);
        result = 31 * result + Arrays.hashCode(link_layer_address_length);
        result = 31 * result + Arrays.hashCode(link_layer_address);
        result = 31 * result + Arrays.hashCode(protocol_type);
        return result;
    }

    @Override
    public String toString() {
        return "LinuxSllV1Header{" +
                "packet_type=" + Arrays.toString(packet_type) +
                ", link_layer_address_type=" + Arrays.toString(link_layer_address_type) +
                ", link_layer_address_length=" + Arrays.toString(link_layer_address_length) +
                ", link_layer_address=" + Arrays.toString(link_layer_address) +
                ", protocol_type=" + Arrays.toString(protocol_type) +
                '}';
    }
}
