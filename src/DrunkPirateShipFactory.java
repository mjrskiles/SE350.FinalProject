import javafx.scene.layout.AnchorPane;

public class DrunkPirateShipFactory extends PirateShipFactory {
    public DrunkPirateShipFactory(AnchorPane root, Ship columbus, Map map) { super(columbus, map); }

    public PirateShip createPirateShip(int x, int y) {
        return new DrunkenPirateShip(columbus, map.getMap());
    }
}
