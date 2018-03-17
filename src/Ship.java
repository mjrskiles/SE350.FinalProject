import java.util.Observable;

import java.awt.*;

/**
 * Created by brennenstenson on 3/16/18.
 */
public class Ship extends Observable {
    protected Point position;
    protected Map map;
    public boolean hasTreasure = false;
    public boolean hitPirate = false;
    public boolean hitMonster = false;

    Point getLocation() {
        return position;
    }

    void move(int x, int y) {}
    void goEast() {}
    void goWest() {}
    void goNorth() {}
    void goSouth() {}
}
