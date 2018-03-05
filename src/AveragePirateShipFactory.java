import javafx.scene.image.ImageView;

public class AveragePirateShipFactory extends PirateShipFactory {
    public AveragePirateShipFactory(Ship columbus, Map map) { super(columbus, map); }

    public PirateShip createPirateShip(int x, int y) {
        PirateShip ps = new AveragePirateShip(columbus, map.getMap());
        ps.setLocation(x, y);
        ps.setImageView(new ImageView(createNewImage()));
        return ps;
    }
}
