import javafx.scene.image.ImageView;

public class AveragePirateShipFactory extends PirateShipFactory {
    public AveragePirateShipFactory() { super(); }

    public PirateShip createPirateShip(Ship columbus, int x, int y) {
        Map map = Map.getInstance();
        PirateShip ps = new AveragePirateShip(columbus, map.getMap());
        ps.setLocation(x, y);
        ps.setImageView(new ImageView(createNewImage()));
        return ps;
    }
}
