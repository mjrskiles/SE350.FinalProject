/**
 * Created by brennenstenson on 3/16/18.
 */
public abstract class ShipDecorator extends Ship {
    protected Ship decoratedShip;

    public ShipDecorator(Ship decoratedShip){
        this.decoratedShip = decoratedShip;
    }
}
