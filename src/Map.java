

import java.awt.Point;
import java.util.Random;

public class Map {
	private static final int DIMENSION = 20;
	private final int NUM_PIRATES = 10;
	private int[][] map = new int[DIMENSION][DIMENSION];
	private static Map uniqueInstance;

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
			if(map[y][x] == CellTypes.ocean()) {
				map[y][x] = CellTypes.pirate();
				count--;
			}
		}
	}

	private void populateMap() {
		for(int[] row : map) {
			for(int i = 0; i < row.length; i++) {
				row[i] = 0;
			}
		}

		int numberOfIslands = 50;
		Random rand = new Random();
		Point myPoint = new Point(rand.nextInt(DIMENSION), rand.nextInt(DIMENSION));

		for(int i = 0; i < numberOfIslands; i++){
			while (map[myPoint.x][myPoint.y] != 0) {
				rand = new Random();
				myPoint = new Point(rand.nextInt(DIMENSION), rand.nextInt(DIMENSION));
			}
			map[myPoint.x][myPoint.y] = 1;
			myPoint = new Point(rand.nextInt(DIMENSION), rand.nextInt(DIMENSION));
		}

		Random randomT = new Random();
		int rowT = randomT.nextInt(map.length);
		int colT = randomT.nextInt(map[rowT].length);
		map[rowT][colT] = 3;
	}

	public int[][] getMap() {
		return map;
	}

	public void updateCell(int x, int y, int type) {
		map[y][x] = type;
	}

	public boolean isOcean(int x, int y) {
		boolean validIndex = false;
		if (x >= 0 && y >= 0 && x < map[0].length && y < map.length)
			validIndex = true;
		return (validIndex && map[y][x] == CellTypes.ocean());
	}

	public int getDimension() { return DIMENSION; }
}
