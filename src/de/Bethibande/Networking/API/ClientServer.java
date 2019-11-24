package de.Bethibande.Networking.API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.Listeners.PacketReceivedEvent;
import de.Bethibande.Networking.API.Listeners.PacketReceivedFromClientEvent;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServer extends Thread {

    @Getter
    private Socket s;
    @Getter
    private ServerSocket serverSocket;
    private Gson g = new Gson();
    @Getter
    private TCPServer server;

    public ClientServer(ServerSocket ss, TCPServer server) {
        super("ClientServer");
        this.serverSocket = ss;
        this.server = server;
    }

    public void run() {
        try {
            while(s == null || (!s.isConnected() && !s.isBound())) {
                s = serverSocket.accept();
            }
            Log.log(Log.LOG_LEVEL.INFO, "Socket connected: " + s.getInetAddress() + ":" + s.getPort());
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while(true) {
                String s = reader.readLine();
                if(s != null && !s.equals("")) {
                    //System.out.println(s);

                    JsonObject jobj = g.fromJson(s, JsonObject.class);
                    String className = jobj.get("className").getAsString();
                    Packet p = (Packet)g.fromJson(s, Class.forName(className));

                    PacketReceivedEvent pre = new PacketReceivedEvent(p);
                    EventManager.runEvent(pre);
                    PacketReceivedFromClientEvent prfce = new PacketReceivedFromClientEvent(this, p);
                    EventManager.runEvent(prfce);

                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void kill(String reason) {
        try {
            System.out.println("Killed server: " + s.getInetAddress() + ":" + s.getPort() + ", " + reason);

            this.s.close();
            serverSocket.close();

            interrupt();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
