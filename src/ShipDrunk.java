import java.awt.*;
import java.util.Random;

/**
 * Created by brennenstenson on 3/16/18.
 */

public class ShipDrunk extends ShipDecorator{
    protected static Point position = Ship.position;
    protected static Map map = Ship.map;
    public boolean hasTreasure = false;
    public boolean hitPirate = false;
    public boolean hitMonster = false;

    public ShipDrunk (ShipInterface decoratedShip){
        super(decoratedShip);
    }
    boolean isRandom = false;


    public void go(){
        Random rand = new Random();
        isRandom = true;
        if(rand.nextInt(4) == 0){
            goEast();
        }
        else if (rand.nextInt(4) == 1){
            goWest();
        }
        else if (rand.nextInt(4) == 2){
            goNorth();
        }
        else if (rand.nextInt(4) == 3){
            goSouth();
        }

    }

    @Override
    public void goEast() {

        // gives 50-50 chance to move ship at random.
        Random a = new Random();

        // if a == 0, then ship moves normally
        if(a.nextInt(2) == 0){
            isRandom = true;
        }

        // if a == 1, then ship moves randomly.
        if(isRandom == false){
            go();
            return;
        }

        final int[][] grid  = map.getMap();
        final int bounds = grid[0].length;

        if(position.x + 1 < bounds) {
            if(grid[position.y][position.x + 1] == CellTypes.ocean) {
                move(position.x + 1, position.y);
            }
            else if(grid[position.y][position.x + 1] == CellTypes.treasure) {
                move(position.x + 1, position.y);
                hasTreasure = true;
            }

            else if(grid[position.y][position.x + 1] == CellTypes.pirate){
                hitPirate = true;
            }

            else if(grid[position.y][position.x + 1] == CellTypes.monster){
                hitMonster = true;
            }

        }
        isRandom = false;
    }

    public void goWest() {

        // gives 50-50 chance to move ship at random.
        Random a = new Random();

        // if a == 0, then ship moves normally
        if(a.nextInt(2) == 0){
            isRandom = true;
        }

        // sends ship to go method, creating random move
        if(isRandom == false){
            go();
            return;
        }

        final int[][] grid = map.getMap();

        if(position.x - 1 >= 0){
            if(grid[position.y][position.x - 1] == CellTypes.ocean) {
                move(position.x - 1, position.y);
            }
            else if(grid[position.y][position.x - 1] == CellTypes.treasure) {

                move(position.x - 1, position.y);
                hasTreasure = true;
            }
            else if(grid[position.y][position.x - 1] == CellTypes.pirate) {
                hitPirate = true;
            }

            else if(grid[position.y][position.x - 1] == CellTypes.monster){
                hitMonster = true;
            }

        }
        isRandom = false;

    }

    public void goNorth() {

        // gives 50-50 chance to move ship at random.
        Random a = new Random();

        // if a == 0, then ship moves normally
        if(a.nextInt(2) == 0){
            isRandom = true;
        }

        // sends ship to go method, creating random move
        if(isRandom == false){
            go();
            return;
        }

        final int[][] grid = map.getMap();

        if(position.y - 1 >= 0) {
            if(grid[position.y - 1][position.x] == CellTypes.ocean) {
                move(position.x, position.y - 1);
            }
            else if(grid[position.y - 1][position.x] == CellTypes.treasure) {
                move(position.x, position.y - 1);
                hasTreasure = true;
            }

            else if(grid[position.y - 1][position.x] == CellTypes.pirate) {
                hitPirate = true;
            }

            else if(grid[position.y - 1][position.x] == CellTypes.monster){
                hitMonster = true;
            }

        }
        isRandom = false;

    }

    public void goSouth() {

        // gives 50-50 chance to move ship at random.
        Random a = new Random();

        // if a == 0, then ship moves normally
        if(a.nextInt(2) == 0){
            isRandom = true;
        }

        // sends ship to go method, creating random move
        if(isRandom == false){
            go();
            return;
        }

        final int[][] grid = map.getMap();
        final int bounds = grid.length;

        if (position.y + 1 < bounds) {
            if (grid[position.y + 1][position.x] == CellTypes.ocean) {
                move(position.x, position.y + 1);
            } else if (grid[position.y + 1][position.x] == CellTypes.treasure) {
                move(position.x, position.y + 1);
                hasTreasure = true;
            } else if (grid[position.y + 1][position.x] == CellTypes.pirate) {
                hitPirate = true;
            } else if (grid[position.y + 1][position.x] == CellTypes.monster) {
                hitMonster = true;
            }

        }
        isRandom = false;

    }


}
