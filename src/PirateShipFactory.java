
import javafx.scene.layout.AnchorPane;

public abstract class PirateShipFactory {
    AnchorPane root;
    Ship columbus;
    Map map;

    public PirateShipFactory(AnchorPane root, Ship columbus, Map map) {
        this.root = root;
        this.columbus = columbus;
        this.map = map;
    }

    public abstract PirateShip createPirateShip(int x, int y);
}
