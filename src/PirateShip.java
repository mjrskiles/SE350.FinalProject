import javafx.scene.image.ImageView;

import java.awt.Point;
import java.util.Observer;
import java.util.Observable;

public class PirateShip implements Observer {
    private Point position;
    private Ship columbus;
    private int[][] map;
    private PursuitStrategy strategy;
    private ImageView pirateImageView;
    private final int scalingFactor = 20;

    public PirateShip(Ship columbus, int[][] map) {
      // Set Observable
      this.columbus = columbus;
      // Default
      position = new Point(0, 0);
      // get copy of Map
      this.map = map;
      setStrategy(new DirectPathStrategy());
    }

    public void setStrategy(PursuitStrategy strategy) { this.strategy = strategy; }

    public void setLocation(int x, int y) {
      position = new Point(x, y);
    }

    private void move(int x, int y) {
      position.move(x, y);
      map[y][x] = CellTypes.pirate();
      if(getLocation().equals(columbus.getLocation())) {
          columbus.hitPirate = true;
      }
    }

    public Point getLocation() {
      return position;
    }

    public void setImageView(ImageView iv) {
        pirateImageView = iv;
        updateImageView();
    }

    public ImageView getImageView() { return pirateImageView; }

    public void update(Observable obs, Object obj) {
        Point columbusLoc = columbus.getLocation();
        //Only attempt to move if the pirate isn't already on Columbus.
        if (position != columbusLoc) {
            Point nextPosition = strategy.getNextPosition(position, columbusLoc);
            move((int) nextPosition.getX(), (int) nextPosition.getY());
        }
        updateImageView();
    }

    private void updateImageView() {
        pirateImageView.setX(getLocation().x * scalingFactor);
        pirateImageView.setY(getLocation().y * scalingFactor);
    }
}
