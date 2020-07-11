package de.Bethibande.Networking.API.Listeners;

import de.Bethibande.Networking.API.ClientServer;
import lombok.Getter;

public class ClientDisconnectEvent extends Event {

    @Getter
    private final String address;
    @Getter
    private final int port;
    @Getter
    private final ClientServer server;

    public ClientDisconnectEvent(String address, int port, ClientServer server) {
        this.address = address;
        this.port = port;
        this.server = server;
    }

}
