package de.Bethibande.Networking.API;

import lombok.Getter;

public class PingPacket extends Packet {

    @Getter
    private long pingTimeInSec;

    public PingPacket(long pingTimeInSec) {
        this.pingTimeInSec = pingTimeInSec;
    }

}
