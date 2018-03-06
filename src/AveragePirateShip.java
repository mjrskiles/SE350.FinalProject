
public class AveragePirateShip extends PirateShip {
    public AveragePirateShip(Ship columbus, int[][] map) {
        super(columbus, map);
        setStrategy(new DirectPathStrategy());
    }
}
