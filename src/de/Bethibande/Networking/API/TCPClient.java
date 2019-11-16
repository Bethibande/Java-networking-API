package de.Bethibande.Networking.API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.Listeners.PacketReceivedEvent;
import de.Bethibande.Networking.API.util.PingEvent;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient extends Thread {

    public static PingEvent pe;

    @Getter
    private String address;
    @Getter
    private int port;
    private Gson g = new Gson();
    @Getter
    private Socket s;

    public TCPClient(String address, int port) {
        super("TCPClient");
        this.address = address;
        this.port = port;
    }

    public void run() {
        try {

            if(pe == null) {
                pe = new PingEvent(this);
                EventManager.addListener(pe);
            }

            s = new Socket(address, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while(s != null && s.isConnected() && !s.isClosed() && s.isBound()) {
                String st = reader.readLine();
                if(st != null && !st.trim().isEmpty()) {
                    System.out.println("in: " + st);
                    JsonObject jobj = g.fromJson(st, JsonObject.class);
                    String className = jobj.get("className").getAsString();
                    Packet p = (Packet)g.fromJson(st, Class.forName(className));

                    PacketReceivedEvent pre = new PacketReceivedEvent(p);
                    EventManager.runEvent(pre);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kill() {
        try {
            System.out.println("Kill Client!");
            if (s != null) {
                s.close();
            }
            interrupt();
            return;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
