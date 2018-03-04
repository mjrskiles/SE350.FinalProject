import com.sun.javafx.geom.Vec2d;

import java.awt.Point;

public class DirectPathStrategy implements PursuitStrategy {
    Map map;

    public DirectPathStrategy() {
        map = Map.getInstance();
    }

    @Override
    public Point getNextPosition(Point currentPosition, Point targetPosition) {
        int dx = currentPosition.x - targetPosition.x;
        int dy = currentPosition.y - targetPosition.y;
        Point nextPosition = new Point(currentPosition.x, currentPosition.y);

        int xDirection = (dx > 0) ? -1 : 1;
        int yDirection = (dy > 0) ? -1 : 1;
        // If the difference in x positions is greater, prefer to move horizontally
        if (Math.abs(dx) > Math.abs(dy)) {
            if (map.isOcean(currentPosition.x + xDirection, currentPosition.y)) {
                nextPosition.x = nextPosition.x + xDirection;
            }
            //if the horizontal move was impossible, try to move vertically.
            else if (map.isOcean(currentPosition.x, currentPosition.y + yDirection)) {
                nextPosition.y = nextPosition.y + yDirection;
            }
        }
        // Try to move vertically if the y distance is greater.
        else {
            if (map.isOcean(currentPosition.x, currentPosition.y + yDirection)) {
                nextPosition.y = nextPosition.y + yDirection;
            }
            else if (map.isOcean(currentPosition.x + xDirection, currentPosition.y)) {
                nextPosition.x = nextPosition.x + xDirection;
            }
        }
        return nextPosition;
    }
}
