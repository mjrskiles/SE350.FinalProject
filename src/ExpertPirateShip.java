public class ExpertPirateShip extends PirateShip {
    public ExpertPirateShip(Ship columbus, int[][] map) {
        super(columbus, map);
        setStrategy(new BFSStrategy());
    }
}
