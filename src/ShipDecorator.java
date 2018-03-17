/**
 * Created by brennenstenson on 3/16/18.
 */
public abstract class ShipDecorator implements ShipInterface{
    protected ShipInterface decoratedShip;

    public ShipDecorator(ShipInterface decoratedShip){
        this.decoratedShip = decoratedShip;
    }

    public void move(int x, int y){
        decoratedShip.move(x,y);
    }
}
