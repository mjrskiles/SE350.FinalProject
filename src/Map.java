

import java.awt.Point;
import java.util.Random;

public class Map {
	private int[][] map = new int[10][10];
	
	public Map() {
		populateMap();
		createPirates();
	}
	
	private void createPirates(int count) {
		Random random = new Random();
		while(count > 0) {
			int y = random.nextInt(grid.length);
			int x = random.nextInt(grid[0].length);
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
	}
	
	public int[][] getMap() {
		return map;
	}
}
