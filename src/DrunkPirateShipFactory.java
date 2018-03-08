import javafx.scene.image.ImageView;

public class DrunkPirateShipFactory extends PirateShipFactory {
    public DrunkPirateShipFactory() { super(); }

    public PirateShip createPirateShip(Ship columbus, int x, int y) {
        Map map = Map.getInstance();
        PirateShip ps = new DrunkenPirateShip(columbus, map.getMap());
        ps.setLocation(x, y);
        ps.setImageView(new ImageView(image));
        return ps;
    }
}
