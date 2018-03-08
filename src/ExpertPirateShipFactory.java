import javafx.scene.image.ImageView;

public class ExpertPirateShipFactory extends PirateShipFactory {
    public ExpertPirateShipFactory() { super(); }

    public PirateShip createPirateShip(Ship columbus, int x, int y) {
        Map map = Map.getInstance();
        PirateShip ps = new ExpertPirateShip(columbus, map.getMap());
        ps.setLocation(x, y);
        ps.setImageView(new ImageView(createNewImage()));
        return ps;
    }
}
