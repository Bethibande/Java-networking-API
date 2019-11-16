import de.Bethibande.Networking.API.Listeners.EventManager;
import de.Bethibande.Networking.API.TCPServer;

public class Main {

    public static void main(String[] args) {
        TCPServer s = new TCPServer(22927);
        s.start();
        //s.setPingTimeout(false);
        EventManager.addListener(new PacketInEvent());
    }
}
