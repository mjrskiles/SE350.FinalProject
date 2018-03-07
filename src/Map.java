

import java.awt.Point;
import java.util.Random;

public class Map {
	private int[][] map = new int[10][10];
	private static Map uniqueInstance;

	private Map() {
		populateMap();
		createPirates(2);
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

		int numberOfIslands = 5;
		Random random = new Random();
		while(numberOfIslands > 0) {
			int row = random.nextInt(map.length);
			int col = random.nextInt(map[row].length);

			map[row][col] = 1;
			numberOfIslands--;
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
}
