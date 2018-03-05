import javafx.scene.layout.AnchorPane;

public class ExpertPirateShipFactory extends PirateShipFactory {
    public ExpertPirateShipFactory(AnchorPane root, Ship columbus, Map map) { super(root, columbus, map); }

    public PirateShip createPirateShip(int x, int y) {
        return new ExpertPirateShip(columbus, map.getMap());
    }
}
