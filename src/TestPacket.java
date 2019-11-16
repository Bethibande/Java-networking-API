import de.Bethibande.Networking.API.Packet;
import lombok.Getter;

public class TestPacket extends Packet {

    @Getter
    private String msg;

    public TestPacket(String msg) {
        this.msg = msg;
    }

}
