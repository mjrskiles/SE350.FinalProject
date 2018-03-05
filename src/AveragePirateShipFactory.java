import javafx.scene.layout.AnchorPane;

public class AveragePirateShipFactory extends PirateShipFactory {
    public AveragePirateShipFactory(AnchorPane root, Ship columbus, Map map) { super(root, columbus, map); }

    public PirateShip createPirateShip(int x, int y) {
        return new AveragePirateShip(columbus, map.getMap());
    }
}
