package de.Bethibande.Networking.API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.util.ServerPingEvent;
import lombok.Getter;
import lombok.Setter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer extends Thread {

    public static ServerPingEvent spe;

    public static boolean debug = true;
    @Getter
    private ServerSocket server;
    @Getter
    private final int port;
    @Getter
    @Setter
    private boolean pingTimeout = true;
    @Getter
    @Setter
    private int timeout = 5000;
    @Getter
    private final List<ClientServer> clients = new ArrayList<>();
    @Getter
    private final PingThread pingThread;

    public TCPServer(int port) {
        super("TCPServer");
        this.port = port;
        this.pingThread = new PingThread(this);
    }

    public void run() {
        try {

            if(spe == null) {
                spe = new ServerPingEvent(this);
                EventManager.addListener(spe);
            }

            server = new ServerSocket(this.port);
            this.pingThread.start();
            if(debug) Log.log(Log.LOG_LEVEL.INFO, "TCP Server started at 127.0.0.1:" + port);
            while (true) {
                Socket s = server.accept();
                if(s != null && s.isBound() && s.isConnected()) {
                    ServerSocket socket = new ServerSocket(0);
                    PortPacket pp = new PortPacket(socket.getLocalPort());
                    SendPacket.sendPacket(s, pp);
                    ClientServer cs = new ClientServer(socket, this);
                    cs.start();
                    s.close();
                    clients.add(cs);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
