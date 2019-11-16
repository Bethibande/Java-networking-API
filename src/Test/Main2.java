package Test;

import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.SendPacket;
import de.Bethibande.Networking.API.TCPClient;
import de.Bethibande.Networking.API.TCPConnector;

public class Main2 {

    public static void main(String[] args) {
        TCPConnector connector = new TCPConnector();
        connector.connect("localhost", 22927);
        connector.waitForConnection();
        System.out.println("Connected!");
        EventManager.removeListener(connector.getPp());
        TCPClient c = connector.getC();
        EventManager.addListener(new PacketInEvent(c));
        TestPacket tp = new TestPacket("abc");
        SendPacket.sendPacket(c.getS(), tp);
        SendPacket.sendPacket(c.getS(), tp);
    }

}
