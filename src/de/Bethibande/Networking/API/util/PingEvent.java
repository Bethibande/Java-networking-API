package de.Bethibande.Networking.API.util;

import de.Bethibande.Networking.API.*;
import de.Bethibande.Networking.API.Listeners.EventHandler;
import de.Bethibande.Networking.API.Listeners.Listener;
import de.Bethibande.Networking.API.Listeners.PacketReceivedEvent;

public class PingEvent implements Listener {

    private TCPClient client;

    public PingEvent(TCPClient client) {
        this.client = client;
    }

    @EventHandler
    public void onPing(PacketReceivedEvent e) {
        Packet p = e.getP();
        if(p instanceof PingPacket) {
            try {
                // without this the server doesn't recognize the packet which is send back :/
                Thread.sleep(2);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
            SendPacket.sendPacket(client.getS(), e.getP());
        }
    }

}
