package de.Bethibande.Networking.API.util;

import de.Bethibande.Networking.API.Listeners.EventHandler;
import de.Bethibande.Networking.API.Listeners.Listener;
import de.Bethibande.Networking.API.Listeners.PacketReceivedEvent;
import de.Bethibande.Networking.API.Packet;
import de.Bethibande.Networking.API.TCPConnector;
import lombok.Getter;

public class PortPacket implements Listener {

    @Getter
    private TCPConnector conn;

    public PortPacket(TCPConnector conn) {
        this.conn = conn;
    }

    @EventHandler
    public void onPacketReceived(PacketReceivedEvent e) {
        Packet p = e.getP();
        if(p instanceof de.Bethibande.Networking.API.PortPacket) {
            de.Bethibande.Networking.API.PortPacket pp = (de.Bethibande.Networking.API.PortPacket)p;
            conn.connectToPort(pp.getPort());
            System.out.println("Connect to port " + pp.getPort());
        }
    }

}
