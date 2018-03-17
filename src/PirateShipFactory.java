
import javafx.scene.image.Image;

public abstract class PirateShipFactory {
    Image image;
    String imagePath = "file:src/pirateShip.png";
    int scalingFactor = Main.scalingFactor;

    public PirateShipFactory() {
        setImageFromPath(imagePath);
    }

    public abstract PirateShip createPirateShip(Ship columbus, int x, int y);

    public void setImageFromPath(String path) {
        imagePath = path;
        image = new Image(path,scalingFactor, scalingFactor, true, true);
    }
}
