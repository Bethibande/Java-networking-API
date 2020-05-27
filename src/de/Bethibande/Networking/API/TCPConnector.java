package de.Bethibande.Networking.API;

import com.sun.awt.AWTUtilities;
import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.util.PortPacket;
import de.Bethibande.Networking.API.util.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class TCPConnector {

    @Getter
    private TCPClient client;
    @Getter
    @Setter
    private boolean connected = false;
    @Getter
    private PortPacket pp;
    private String address;

    public void connect(String address, int port) {
        pp = new PortPacket(this);
        EventManager.addListener(pp);
        client = new TCPClient(address, port);
        client.start();
        this.address = address;

    }

    public void waitForConnection() {
        while(!connected) {
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        EventManager.removeListener(pp);
    }

    public void connectToPort(int port) {
        client.kill();
        client = new TCPClient(this.address, port);
        client.start();
        connected = true;
    }

}
