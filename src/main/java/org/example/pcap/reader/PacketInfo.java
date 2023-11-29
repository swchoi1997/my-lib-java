package org.example.pcap.reader;

public record PacketInfo(Integer start, Integer end) {

    public Integer getLength() {
        return end - start;
    }


}
