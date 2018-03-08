public class DrunkenPirateShip extends PirateShip {
    public DrunkenPirateShip(Ship columbus, int[][] map) {
        super(columbus, map);
        setStrategy(new DrunkenStrategy());
    }
}
