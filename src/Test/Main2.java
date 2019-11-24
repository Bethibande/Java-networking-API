package Test;

import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.Log;
import de.Bethibande.Networking.API.SendPacket;
import de.Bethibande.Networking.API.TCPClient;
import de.Bethibande.Networking.API.TCPConnector;

public class Main2 {

    public static void main(String[] args) {
        //start the client
        TCPConnector connector = new TCPConnector();
        connector.connect("localhost", 22927);
        connector.waitForConnection();

        Log.log(Log.LOG_LEVEL.INFO, "Connected!");

        TCPClient client = connector.getC();

        //other stuff
        EventManager.addListener(new PacketInEvent(client));
        TestPacket tp = new TestPacket("abc");
        SendPacket.sendPacket(client.getS(), tp);
        SendPacket.sendPacket(client.getS(), tp);
    }

}
