package com.example.lameparsermap.parser;

import com.example.lameparsermap.structs.EthernetFrame;
import com.example.lameparsermap.structs.Ipv4Packet;
import com.example.lameparsermap.structs.Pcap;

import java.io.IOException;
import java.util.Arrays;

public class MainKaitai {

    public static void main(String[] args) throws IOException {
        Pcap pcap = Pcap.fromFile("src/main/resources/gsm_map_with_ussd_string.pcap");
        ((Ipv4Packet) ((EthernetFrame) pcap.packets().get(0).body()).body()).body()._io().readBytes(144); // read until map
        byte[] mapBytes = ((Ipv4Packet) ((EthernetFrame) pcap.packets().get(0).body()).body()).body()._io().readBytesFull();

        byte invokeId = mapBytes[4];
        byte opCode = mapBytes[7];
        byte[] ussdBytes = Arrays.copyOfRange(mapBytes, 15, 29);
        String ussdString = javax.xml.bind.DatatypeConverter.printHexBinary(ussdBytes);

        System.out.println("invokeId = " + invokeId);
        System.out.println("opCode = " + opCode);
        System.out.println("ussdString = " + ussdString);
    }
}
