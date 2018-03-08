
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public abstract class PirateShipFactory {
    Image image;
    String imagePath = "file:src/pirateShip.png";

    public PirateShipFactory() {
        setImageFromPath(imagePath);
    }

    public abstract PirateShip createPirateShip(Ship columbus, int x, int y);

    public void setImageFromPath(String path) {
        imagePath = path;
        image = new Image(path,50, 50, true, true);
    }
}
