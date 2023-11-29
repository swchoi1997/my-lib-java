package org.example.pcap.constants.protocol;

import org.example.pcap.header.protocol.ProtocolPacket;
import org.example.pcap.header.protocol.TcpPacket;
import org.example.pcap.header.protocol.UdpPacket;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProtocolType {

    TCP(6, TcpPacket::new, 20),
    UDP(17, UdpPacket::new, 8)
    ;

    private final Integer type;
    private final Function<ByteBuffer, ProtocolPacket> protocolPacket;
    private final Integer length;

    private static final Map<Integer, ProtocolType> PROTOCOL_TYPE = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(ProtocolType::getType, Function.identity()))
    );

    ProtocolType(final Integer type,
             final Function<ByteBuffer, ProtocolPacket> protocolPacket,
             final Integer length) {
        this.type = type;
        this.protocolPacket = protocolPacket;
        this.length = length;
    }

    public static Function<ByteBuffer, ProtocolPacket> getProtocolType(final int type) {
        return ProtocolType.PROTOCOL_TYPE.get(type).getProtocolPacket();
    }
    public static Integer getProtocolTypeLength(final int type) {
        return ProtocolType.PROTOCOL_TYPE.get(type).getLength();
    }

    public Integer getType() {
        return type;
    }

    public Function<ByteBuffer, ProtocolPacket> getProtocolPacket() {
        return protocolPacket;
    }

    public Integer getLength() {
        return length;
    }
}
