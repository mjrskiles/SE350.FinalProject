
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public abstract class PirateShipFactory {
    Ship columbus;
    Map map;
    Image image;
    String imagePath;
    int scalingFactor = 20;

    public PirateShipFactory(Ship columbus, Map map) {
        this.columbus = columbus;
        this.map = map;
    }

    public abstract PirateShip createPirateShip(int x, int y);

    public void setImageFromPath(String path) {
        imagePath = path;
        image = new Image(getClass().getResource(path).toExternalForm(),
                scalingFactor, scalingFactor, true, true);
    }

    public Image createNewImage() {
        return new Image(getClass().getResource(imagePath).toExternalForm(),
                scalingFactor, scalingFactor, true, true);
    }
}
