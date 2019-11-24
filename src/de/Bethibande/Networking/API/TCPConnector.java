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
    private TCPClient c;
    @Getter
    @Setter
    private boolean connected = false;
    @Getter
    private PortPacket pp;
    private String address;

    public void connect(String address, int port) {
        pp = new PortPacket(this);
        EventManager.addListener(pp);
        c = new TCPClient(address, port);
        c.start();
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
        c.kill();
        c = new TCPClient(this.address, port);
        c.start();
        connected = true;
    }

}
