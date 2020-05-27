package Test;

import de.Bethibande.Networking.API.Listeners.EventHandler;
import de.Bethibande.Networking.API.Listeners.Listener;
import de.Bethibande.Networking.API.Listeners.PacketReceivedEvent;
import de.Bethibande.Networking.API.TCPClient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PacketInEvent implements Listener {

    private TCPClient c = null;

    public PacketInEvent(TCPClient c) {
        this.c = c;
    }

    @EventHandler
    public void onPacketReceived(PacketReceivedEvent e) {
        if(e.getPacket() instanceof TestPacket) {
            TestPacket tp = (TestPacket)e.getPacket();
            System.out.println("IN: " + tp.getMsg());
        }
    }

}
