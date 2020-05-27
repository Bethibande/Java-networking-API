package de.Bethibande.Networking.API.Listeners;

import de.Bethibande.Networking.API.Packet;
import lombok.Getter;

public class PacketReceivedEvent extends Event {

    @Getter
    private final Packet packet;

    public PacketReceivedEvent(Packet p) {
        this.packet = p;
    }

}
