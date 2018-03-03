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
        Point nextPosition;

        // If the difference in x positions is greater, prefer to move horizontally
        boolean success;
        if(Math.abs(dx) > Math.abs(dy)) {
            int direction = (dx > 0) ? -1 : 1;

        }

        return currentPosition;
    }
}
