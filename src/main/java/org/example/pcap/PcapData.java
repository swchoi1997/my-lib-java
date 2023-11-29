package org.example.pcap;

import org.example.pcap.header.GlobalHeader;
import org.example.pcap.header.PacketHeader;
import org.example.pcap.reader.PacketBody;


import java.util.List;
import java.util.Objects;

public class PcapData {

    private final GlobalHeader globalHeader;
    private final List<PacketHeader> packetHeaders;
    private final List<PacketBody> packetBodies;

    public PcapData(final GlobalHeader globalHeader,
                    final List<PacketHeader> packetHeaders,
                    final List<PacketBody> packetBodies) {
        this.globalHeader = globalHeader;
        this.packetHeaders = packetHeaders;
        this.packetBodies = packetBodies;
    }

    public GlobalHeader getGlobalHeader() {
        return globalHeader;
    }

    public List<PacketHeader> getPacketHeaders() {
        return packetHeaders;
    }

    public List<PacketBody> getPacketBodies() {
        return packetBodies;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PcapData pcapData = (PcapData) o;
        return Objects.equals(globalHeader, pcapData.globalHeader) && Objects.equals(packetHeaders, pcapData.packetHeaders) && Objects.equals(packetBodies, pcapData.packetBodies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalHeader, packetHeaders, packetBodies);
    }

    @Override
    public String toString() {
        return "PcapData{" +
                "globalHeader=" + globalHeader +
                ", packetHeaders=" + packetHeaders +
                ", packetBodies=" + packetBodies +
                '}';
    }
}
