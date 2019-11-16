package de.Bethibande.Networking.API;

import lombok.Getter;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PingThread extends Thread {

    public static List<ClientServer> pinged = new ArrayList<>();

    @Getter
    private TCPServer server;

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
                    for (ClientServer s : server.getClients()) {
                        if(s.getS().isConnected() && !pinged.contains(s)) {
                            PingPacket pp = new PingPacket(getTime());
                            try {
                                SendPacket.sendPacket(s.getS(), pp);
                            } catch (Exception e) {
                                s.kill("Couldn't ping the client!");
                                continue;
                            }
                            pinged.add(s);
                        } else {
                            pinged.remove(s);
                            s.kill("Client didn't ping back!");
                            offline.add(s);
                        }
                    }
                    for(ClientServer s : offline) {
                        server.getClients().remove(s);
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
