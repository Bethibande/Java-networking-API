package de.Bethibande.Networking.API;

import lombok.Getter;

public class PortPacket extends Packet {

    @Getter
    private int port;

    public PortPacket(int port) {
        this.port = port;
    }

}
