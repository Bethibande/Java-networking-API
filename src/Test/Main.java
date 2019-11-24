package Test;

import Test.PacketInEvent;
import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.TCPServer;

public class Main {

    public static void main(String[] args) {
        // start the server
        TCPServer s = new TCPServer(22927);
        s.start();
        s.setTimeout(5000);
        //s.setPingTimeout(false);

        //other stuff
        EventManager.addListener(new PacketInEvent());
    }
}
