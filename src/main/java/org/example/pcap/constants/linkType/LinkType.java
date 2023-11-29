package org.example.pcap.constants.linkType;

import org.example.pcap.header.linkType.EthernetHeader;
import org.example.pcap.header.linkType.LinkTypeHeader;
import org.example.pcap.header.linkType.LinuxSllV1Header;
import org.example.pcap.header.linkType.LinuxSllV2Header;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum LinkType {
    ETHERNET(1, EthernetHeader::new, 14),
    LINUX_SLL_V1(113, LinuxSllV1Header::new, 16),
    LINUX_SLL_V2(276, LinuxSllV2Header::new, 20)
    ;

    private static final Map<Integer, LinkType> LINK_TYPE = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(LinkType::getType, Function.identity())));

    private final Integer type;
    private final Function<ByteBuffer, LinkTypeHeader> linkTypeHeader;
    private final Integer length;

    LinkType(final Integer type,
             final Function<ByteBuffer, LinkTypeHeader> linkTypeHeader,
             final Integer length) {
        this.type = type;
        this.linkTypeHeader = linkTypeHeader;
        this.length = length;
    }

    public static Function<ByteBuffer, LinkTypeHeader> getLinkType(final int type) {
        return LinkType.LINK_TYPE.get(type).getLinkTypeHeader();
    }

    public static Integer getLength(final int type) {
        return LinkType.LINK_TYPE.get(type).getLength();
    }

    public Integer getType() {
        return type;
    }

    public Function<ByteBuffer, LinkTypeHeader> getLinkTypeHeader() {
        return linkTypeHeader;
    }

    public Integer getLength() {
        return length;
    }
}
