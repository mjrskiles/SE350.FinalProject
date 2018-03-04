import java.awt.*;
import java.util.Random;

public class DrunkenStrategy implements PursuitStrategy {
    @Override
    public Point getNextPosition(Point currentLocation, Point targetLocation) {
        Map map = Map.getInstance();
        Random rand = new Random();

        Point next;
        int tried = 0;
        do {
            //Get a random direction. 0 is north, 1 is east, 2 is south, 3 is west
            int direction = rand.nextInt(4);
            next = new Point(currentLocation.x, currentLocation.y);

            switch (direction) {
                case 0:
                    next.y -= 1;
                    break;
                case 1:
                    next.x += 1;
                    break;
                case 2:
                    next.y += 1;
                    break;
                case 3:
                    next.x -= 1;
                    break;
            }

            //prevent an infinite loop. If all directions have been tried, do nothing.
            tried += 1;
        } while (tried < 4 && !map.isOcean(next.x, next.y));
        
        return next;
    }
}
