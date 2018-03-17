
import java.awt.Point;
import java.util.Random;

public class SoberColumbusShip extends Ship {


	public SoberColumbusShip(){}

	public SoberColumbusShip(Map map) {
		Random random = new Random();
		while(true) {
			int x = random.nextInt(map.getMap()[0].length);
			int y = random.nextInt(map.getMap().length);
			if(map.getMap()[y][x] == CellTypes.ocean) {
				position = new Point(x, y);
				break;
			}
		}
		this.map = map;
	}

	@Override
	public Point getLocation() {
		return position;
	}

	@Override
	public void move(int x, int y) {
		position = new Point(x, y);
		setChanged();
		notifyObservers();
	}

	@Override
	public void goEast() {
		// go right
		final int[][] grid  = map.getMap();
		final int bounds = grid[0].length;

		if(position.x + 1 < bounds) {
			int cell = grid[position.y][position.x + 1];
			if(cell == CellTypes.ocean || cell == CellTypes.rum) {
                map.updateCell(position.x, position.y, CellTypes.ocean);
                move(position.x + 1, position.y);
			}
			else if(cell == CellTypes.treasure) {
				move(position.x + 1, position.y);
				hasTreasure = true;
			}

			else if(cell == CellTypes.pirate){
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}

		}
	}

	@Override
	public void goWest() {

		final int[][] grid = map.getMap();

		if(position.x - 1 >= 0){
		    int cell = grid[position.y][position.x - 1];
            if(cell == CellTypes.ocean || cell == CellTypes.rum) {
                map.updateCell(position.x, position.y, CellTypes.ocean);
                move(position.x - 1, position.y);
			}
			else if(cell == CellTypes.treasure) {

				move(position.x - 1, position.y);
				hasTreasure = true;
			}
			else if(cell == CellTypes.pirate) {
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}

		}
	}

	@Override
	public void goNorth() {

		final int[][] grid = map.getMap();

		if(position.y - 1 >= 0) {
		    int cell = grid[position.y - 1][position.x];
            if(cell == CellTypes.ocean || cell == CellTypes.rum) {
                map.updateCell(position.x, position.y, CellTypes.ocean);
                move(position.x, position.y - 1);
			}
			else if(cell == CellTypes.treasure) {
				move(position.x, position.y - 1);
				hasTreasure = true;
			}

			else if(cell == CellTypes.pirate) {
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}

		}
	}

	@Override
	public void goSouth() {

		final int[][]grid = map.getMap();
		final int bounds = grid.length;

		if(position.y + 1 < bounds) {
		    int cell = grid[position.y + 1][position.x];
            if(cell == CellTypes.ocean || cell == CellTypes.rum) {
                map.updateCell(position.x, position.y, CellTypes.ocean);
                move(position.x, position.y + 1);
			}
			else if(cell == CellTypes.treasure) {
				move(position.x, position.y + 1);
				hasTreasure = true;
			}

			else if(cell == CellTypes.pirate) {
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}

		}
	}
}
