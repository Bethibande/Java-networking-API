package de.Bethibande.Networking.API.util;

import de.Bethibande.Networking.API.Listeners.EventHandler;
import de.Bethibande.Networking.API.Listeners.Listener;
import de.Bethibande.Networking.API.Listeners.PacketReceivedFromClientEvent;
import de.Bethibande.Networking.API.Packet;
import de.Bethibande.Networking.API.PingPacket;
import de.Bethibande.Networking.API.PingThread;
import de.Bethibande.Networking.API.TCPServer;

import java.util.Calendar;

public class ServerPingEvent implements Listener {

    private TCPServer server;

    public ServerPingEvent(TCPServer server) {
        this.server = server;
    }

    @EventHandler
    public void onPing(PacketReceivedFromClientEvent e) {
        Packet p = e.getPacket();
        if(p instanceof PingPacket) {
            PingPacket pp = (PingPacket)p;
            PingThread.pinged.remove(e.getClient());
            //System.out.println("Pinged back from " + e.getClient().getS().getInetAddress() + " in " + (getTime()-pp.getPingTimeInSec()) + " sec");
        }
    }

    public long getTime() { return Calendar.getInstance().getTimeInMillis()/1000; }

}
