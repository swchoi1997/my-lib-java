package org.example.pcap.header.linkType;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.example.pcap.constants.linkType.LinuxSllV2HeaderType.*;

public class LinuxSllV2Header extends LinkTypeHeader {

    private final byte[] protocol_type;
    private final byte[] reserved;
    private final byte[] interface_index;
    private final byte[] link_layer_address_type;
    private final byte[] packet_type;
    private final byte[] link_layer_address_length;
    private final byte[] link_layer_address;


    public LinuxSllV2Header(final ByteBuffer buffer) {
        this.protocol_type = this.get(PROTOCOL_TYPE, buffer);
        this.reserved = this.get(RESERVED, buffer);
        this.interface_index = this.get(INTERFACE_INDEX, buffer);
        this.link_layer_address_type = this.get(LINK_LAYER_ADDRESS_TYPE, buffer);
        this.packet_type = this.get(PACKET_TYPE, buffer);
        this.link_layer_address_length = this.get(LINK_LAYER_ADDRESS_LENGTH, buffer);
        this.link_layer_address = this.get(LINK_LAYER_ADDRESS, buffer);
    }

    public byte[] getProtocol_type() {
        return protocol_type;
    }

    public byte[] getReserved() {
        return reserved;
    }

    public byte[] getInterface_index() {
        return interface_index;
    }

    public byte[] getLink_layer_address_type() {
        return link_layer_address_type;
    }

    public byte[] getPacket_type() {
        return packet_type;
    }

    public byte[] getLink_layer_address_length() {
        return link_layer_address_length;
    }

    public byte[] getLink_layer_address() {
        return link_layer_address;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinuxSllV2Header that = (LinuxSllV2Header) o;
        return Arrays.equals(protocol_type, that.protocol_type) && Arrays.equals(reserved, that.reserved) && Arrays.equals(interface_index, that.interface_index) && Arrays.equals(link_layer_address_type, that.link_layer_address_type) && Arrays.equals(packet_type, that.packet_type) && Arrays.equals(link_layer_address_length, that.link_layer_address_length) && Arrays.equals(link_layer_address, that.link_layer_address);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(protocol_type);
        result = 31 * result + Arrays.hashCode(reserved);
        result = 31 * result + Arrays.hashCode(interface_index);
        result = 31 * result + Arrays.hashCode(link_layer_address_type);
        result = 31 * result + Arrays.hashCode(packet_type);
        result = 31 * result + Arrays.hashCode(link_layer_address_length);
        result = 31 * result + Arrays.hashCode(link_layer_address);
        return result;
    }

    @Override
    public String toString() {
        return "LinuxSllV2Header{" +
                "protocol_type=" + Arrays.toString(protocol_type) +
                ", reserved=" + Arrays.toString(reserved) +
                ", interface_index=" + Arrays.toString(interface_index) +
                ", link_layer_address_type=" + Arrays.toString(link_layer_address_type) +
                ", packet_type=" + Arrays.toString(packet_type) +
                ", link_layer_address_length=" + Arrays.toString(link_layer_address_length) +
                ", link_layer_address=" + Arrays.toString(link_layer_address) +
                '}';
    }
}
