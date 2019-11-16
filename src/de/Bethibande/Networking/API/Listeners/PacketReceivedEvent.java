package de.Bethibande.Networking.API.Listeners;

import de.Bethibande.Networking.API.Packet;
import lombok.Getter;

public class PacketReceivedEvent extends Event {

    @Getter
    private Packet p;

    public PacketReceivedEvent(Packet p) {
        this.p = p;
    }

}
