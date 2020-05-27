package de.Bethibande.Networking.API;

import de.Bethibande.Networking.API.Listeners.ClientDisconnectEvent;
import de.Bethibande.Networking.API.Listeners.EventManager;
import lombok.Getter;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PingThread extends Thread {

    public static List<ClientServer> pinged = new ArrayList<>();

    @Getter
    private final TCPServer server;

    public PingThread(TCPServer server) {
        super("PingThread");
        this.server = server;
    }

    public void run() {
        try {
            boolean first = true;
            while (true) {

                if(server.isPingTimeout()) {
                    List<ClientServer> offline = new ArrayList<>();

                    if(!first) {
                        for(ClientServer c : server.getClients()) {
                            if(pinged.contains(c)) {
                                c.kill("Client didn't ping back.");
                                offline.add(c);
                            }
                        }
                    } else first = false;

                    for(ClientServer c : server.getClients()) {
                        if (c.getS().isClosed()) {
                            if(!offline.contains(c)) offline.add(c);
                        } else {
                            PingPacket pp = new PingPacket(getTime());
                            try {
                                SendPacket.sendPacket(c.getS(), pp);
                                pinged.add(c);
                            } catch (Exception e) {
                                c.kill("Couldn't ping client.");
                                offline.add(c);
                            }
                        }
                    }

                    for(ClientServer c : offline) {
                        server.getClients().remove(c);
                        EventManager.runEvent(new ClientDisconnectEvent(c.getS().getInetAddress().getHostAddress(), c.getS().getPort()));
                    }
                    offline.clear();

                }

                sleep(server.getTimeout());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private long getTime() { return Calendar.getInstance().getTimeInMillis()/1000; }

}
