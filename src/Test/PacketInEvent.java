package Test;

import de.Bethibande.Networking.API.Listeners.EventHandler;
import de.Bethibande.Networking.API.Listeners.Listener;
import de.Bethibande.Networking.API.Listeners.PacketReceivedEvent;

public class PacketInEvent implements Listener {

    @EventHandler
    public void onPacketReceived(PacketReceivedEvent e) {
        if(e.getP() instanceof TestPacket) {
            TestPacket tp = (TestPacket)e.getP();
            System.out.println("IN: " + tp.getMsg());
        }
    }

}
