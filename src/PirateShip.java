import java.awt.Point;
import java.util.Observer;
import java.util.Observable;

public class PirateShip implements Observer {
    private Point position;
    private Ship columbus;
    private int[][] map;

    public PirateShip(Ship columbus, int[][] map) {
      // Set Observable
      this.columbus = columbus;
      // Default
      position = new Point(0, 0);
      // get copy of Map
      this.map = map;
    }

    public void setLocation(int x, int y) {
      position = new Point(x, y);
    }

    private void move(int x, int y) {
      position.move(x, y);
    }

    public Point getLocation() {
      return position;
    }

    public void update(Observable obs, Object obj) {
      Point columbusLocation = columbus.getLocation();
      if(position.x < columbusLocation.x) {
        if(map[position.y][position.x + 1] == CellTypes.ocean()) {
          move(position.x + 1, position.y);
          return;
        }
      }
      else if(position.x > columbusLocation.x) {
        if(map[position.y][position.x - 1] == CellTypes.ocean()) {
          move(position.x - 1, position.y);
          return;
        }
      }
      if(position.y < columbusLocation.y) {
        if(map[position.y + 1][position.x] == CellTypes.ocean()) {
          move(position.x, position.y + 1);
          return;
        }
      }
      else if(position.y > columbusLocation.y) {
        if(map[position.y - 1][position.x] == CellTypes.ocean()) {
          move(position.x, position.y - 1);
          return;
        }
      }
    }
}
