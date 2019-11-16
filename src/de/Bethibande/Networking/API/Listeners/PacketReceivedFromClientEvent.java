package de.Bethibande.Networking.API.Listeners;

import de.Bethibande.Networking.API.ClientServer;
import de.Bethibande.Networking.API.Packet;
import lombok.Getter;

public class PacketReceivedFromClientEvent extends Event {

    @Getter
    private Packet packet;
    @Getter
    private ClientServer client;

    public PacketReceivedFromClientEvent(ClientServer client, Packet p) {
        this.client = client;
        this.packet = p;
    }

}
