package org.example.pcap.reader;

import org.example.pcap.PcapData;
import org.example.pcap.body.PacketData;
import org.example.pcap.constants.linkType.LinkType;
import org.example.pcap.constants.protocol.ProtocolType;
import org.example.pcap.header.GlobalHeader;
import org.example.pcap.header.PacketBodyHeader;
import org.example.pcap.header.PacketHeader;
import org.example.pcap.header.linkType.LinkTypeHeader;
import org.example.pcap.header.protocol.ProtocolPacket;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Integer.*;
import static java.nio.ByteBuffer.*;
import static org.example.pcap.constants.GlobalHeaderType.GLOBAL_TOTAL_LENGTH;
import static org.example.pcap.constants.PacketFrontHeaderType.*;
import static org.example.utils.ByteUtils.*;
import static org.example.utils.ByteUtils.toStringArrayReverseEndian;

public class PcapReader implements Reader {

    private final String filePath;

    public PcapReader(final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public PcapData read() {
        try (final FileInputStream fis = new FileInputStream(this.filePath);
             final FileChannel channel = fis.getChannel()) {

            final ByteBuffer globalHeaderByte = allocate(GLOBAL_TOTAL_LENGTH.getPacketInfo().getLength());
            channel.read(globalHeaderByte);

            final GlobalHeader globalHeader = new GlobalHeader(globalHeaderByte.flip());
            List<PacketHeader> packetHeaders = new ArrayList<>();
            List<PacketBody> packetBodies = new ArrayList<>();

            final Function<ByteBuffer, LinkTypeHeader> linkType = LinkType.getLinkType(
                    parseUnsignedInt(
                            toStr(toStringArrayReverseEndian(globalHeader.getLink_type())), 16));

            while(true){
                ByteBuffer packetHeaderBuffer = allocate(PACKET_FRONT_HEADER_TOTAL_LENGTH.getPacketInfo().getLength());

                if (channel.read(packetHeaderBuffer) == -1) break;

                final PacketHeader packetHeader = new PacketHeader(packetHeaderBuffer.flip());
                packetHeaders.add(packetHeader);

                final ByteBuffer packetBodyBuffer = allocate(
                        parseUnsignedInt(toStr(toStringArrayReverseEndian(packetHeader.getActual_packet_length())),
                                16));

                channel.read(packetBodyBuffer);
                packetBodyBuffer.flip();

                final LinkTypeHeader linkTypeHeader = linkType.apply(packetBodyBuffer);
                final PacketBodyHeader packetBodyHeader = new PacketBodyHeader(packetBodyBuffer);
                final ProtocolPacket protocolPacket =
                        ProtocolType.getProtocolType(parseUnsignedInt(
                                toStr(toStringArrayReverseEndian(packetBodyHeader.getProtocol())))).apply(packetBodyBuffer);

                final PacketData packetData = new PacketData(packetBodyBuffer);

                packetBodies.add(
                        new PacketBody(linkTypeHeader, packetBodyHeader, protocolPacket, packetData));
            }

            return new PcapData(globalHeader, packetHeaders, packetBodies);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        Reader pcapReader = new PcapReader("/Users/forest_choi/Downloads/gnss/gnss.pcap1");
        pcapReader.read();
    }
}
