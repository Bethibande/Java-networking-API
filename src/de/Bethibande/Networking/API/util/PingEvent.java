package de.Bethibande.Networking.API.util;

import de.Bethibande.Networking.API.Listeners.EventHandler;
import de.Bethibande.Networking.API.Listeners.Listener;
import de.Bethibande.Networking.API.Listeners.PacketReceivedEvent;
import de.Bethibande.Networking.API.Packet;
import de.Bethibande.Networking.API.PingPacket;
import de.Bethibande.Networking.API.SendPacket;
import de.Bethibande.Networking.API.TCPClient;

public class PingEvent implements Listener {

    private TCPClient client;

    public PingEvent(TCPClient client) {
        this.client = client;
    }

    @EventHandler
    public void onPing(PacketReceivedEvent e) {
        Packet p = e.getP();
        if(p instanceof PingPacket) {
            //already dead
            System.out.println(client.getS().isClosed());
            SendPacket.sendPacket(client.getS(), e.getP());
        }
    }

}
