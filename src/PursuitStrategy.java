import java.awt.Point;

public interface PursuitStrategy {
    /*
     * Returns the next location that the pirate will move to
     * If it is impossible to move, the method should return the pirate's current location (no move)
     */
    Point getNextPosition(Point currentPosition, Point targetPosition);
}
