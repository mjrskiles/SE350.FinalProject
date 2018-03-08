
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public abstract class PirateShipFactory {
    Image image;
    String imagePath;

    public PirateShipFactory() {}

    public abstract PirateShip createPirateShip(Ship columbus, int x, int y);

    public void setImageFromPath(String path) {
        imagePath = path;
        image = new Image(getClass().getResource(path).toExternalForm(),
                50, 50, true, true);
    }

    public Image createNewImage() {
        return new Image(getClass().getResource(imagePath).toExternalForm(),
                50, 50, true, true);
    }
}
