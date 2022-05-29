package com.example.lameparsermap.parser;

import io.pkts.Pcap;
import io.pkts.buffer.Buffer;
import io.pkts.packet.sctp.SctpPacket;
import io.pkts.protocol.Protocol;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        final Pcap pcap = Pcap.openStream("src/main/resources/gsm_map_with_ussd_string.pcap");

        pcap.loop(packet -> {
            System.out.println("started");

            if (packet.hasProtocol(Protocol.SCTP)) {
                System.out.println("sctp");
                SctpPacket sctpPacket = (SctpPacket) packet.getPacket(Protocol.SCTP);
                Buffer buffer = sctpPacket.getPayload();

                System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(buffer.getArray()));

            } else {
                System.out.println("unknown");
            }

            return true;
        });
    }
}
