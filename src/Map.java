

import java.awt.Point;
import java.util.Random;

public class Map {
	private static final int DIMENSION = 20;
	private final int NUM_PIRATES = 7;
	private final int NUM_ISLANDS = 40;
	private final int NUM_RUM_BOTTLES = 10;
	private int[][] map = new int[DIMENSION][DIMENSION];
	private static Map uniqueInstance;
    Random rand = new Random();

	private Map() {
		populateMap();
		createPirates(NUM_PIRATES);
	}

	public static Map getInstance() {
		if(uniqueInstance != null) {
			return uniqueInstance;
		}
		else {
			uniqueInstance = new Map();
			return uniqueInstance;
		}
	}

	/*
	* Pirates will be added to the map after the rest of it is generated.
	*/
	private void createPirates(int count) {
		Random random = new Random();
		while(count > 0) {
			int y = random.nextInt(map.length);
			int x = random.nextInt(map[0].length);
			if(map[y][x] == CellTypes.ocean) {
				map[y][x] = CellTypes.pirate;
				count--;
			}
		}
	}

	private Point getRandomOceanCell() {
	    int x, y;

        do {
            x = rand.nextInt(DIMENSION);
            y = rand.nextInt(DIMENSION);
        } while (!isOcean(x, y));

        return new Point(x, y);
    }

	private void populateMap() {
	    //Place the islands
		for(int i = 0; i < NUM_ISLANDS; i++){
			Point p = getRandomOceanCell();
			updateCell(p.x, p.y, CellTypes.island);
		}

		for(int i = 0; i < NUM_RUM_BOTTLES; i++) {
		    Point p = getRandomOceanCell();
		    updateCell(p.x, p.y, CellTypes.rum);
        }

		//Place the treasure
        Point p = getRandomOceanCell();
		updateCell(p.x, p.y, CellTypes.treasure);
	}

	public int[][] getMap() {
		return map;
	}

	public void updateCell(int x, int y, int type) {
        if (x >= 0 && y >= 0 && x < map[0].length && y < map.length)
		    map[y][x] = type;
	}

	public boolean isOcean(int x, int y) {
		boolean validIndex = false;
		if (x >= 0 && y >= 0 && x < map[0].length && y < map.length)
			validIndex = true;
		return (validIndex && map[y][x] == CellTypes.ocean);
	}

    public boolean isRum(int x, int y) {
        boolean validIndex = false;
        if (x >= 0 && y >= 0 && x < map[0].length && y < map.length)
            validIndex = true;
        return (validIndex && map[y][x] == CellTypes.rum);
    }

	// Returns true if a ship or pirate ship is allowed to enter this cell
	public boolean canEnter(int x, int y) {
	    return isOcean(x, y) || isRum(x, y);
    }

	public int getDimension() { return DIMENSION; }

}
