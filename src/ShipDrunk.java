import java.awt.*;
import java.util.Random;

/**
 * Created by brennenstenson on 3/16/18.
 */

public class ShipDrunk extends ShipDecorator{

    public ShipDrunk (Ship decoratedShip){
        super(decoratedShip);
    }

    @Override
    public Point getLocation() {
        return decoratedShip.getLocation();
    }

    @Override
    public void goEast() {
        decoratedShip.goWest();
    }

    @Override
    public void goWest() {
        decoratedShip.goEast();
    }

    @Override
    public void goNorth() {
        decoratedShip.goSouth();
    }

    @Override
    public void goSouth() {
        decoratedShip.goNorth();
    }
}
