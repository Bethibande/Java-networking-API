package de.Bethibande.Networking.API.Listeners;

import lombok.Getter;

public class ClientDisconnectEvent extends Event {

    @Getter
    private final String address;
    @Getter
    private final int port;

    public ClientDisconnectEvent(String address, int port) {
        this.address = address;
        this.port = port;
    }

}
