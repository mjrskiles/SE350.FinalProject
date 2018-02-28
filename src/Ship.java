
import java.awt.Point;
import java.util.Observable;
import java.util.Random;

public class Ship extends Observable {
	private Point position;
	private Map map;

	public Ship(Map map) {
		Random random = new Random();
		while(true) {
			int x = random.nextInt(map.getMap()[0].length);
			int y = random.nextInt(map.getMap().length);
			if(map.getMap()[y][x] == CellTypes.ocean()) {
				position = new Point(x, y);
				break;
			}
		}
		this.map = map;
	}

	public Point getLocation() {
		return position;
	}

	private void move(int x, int y) {
		position = new Point(x, y);
		setChanged();
		notifyObservers();
	}

	public void goEast() {
		// go right
		final int[][] grid  = map.getMap();
    final int bounds = grid[0].length;

		if(position.x + 1 < bounds  && grid[position.y][position.x + 1] == CellTypes.ocean()) {
			move(position.x + 1, position.y);
		}
	}

	public void goWest() {
		// go left
    final int[][] grid = map.getMap();
    final int bounds = grid[0].length;

		if(position.x - 1 >= 0 && grid[position.y][position.x - 1] == CellTypes.ocean()) {
			move(position.x - 1, position.y);
		}
	}

	public void goNorth() {

	  final int[][] grid = map.getMap();

		if(position.y - 1 >= 0 && grid[position.y - 1][position.x] == CellTypes.ocean()) {
			move(position.x, position.y - 1);
		}
	}

	public void goSouth() {

	  final int[][]grid = map.getMap();
		final int bounds = grid.length;

		if(position.y + 1 < bounds  && grid[position.y + 1][position.x] == CellTypes.ocean()) {
			move(position.x, position.y + 1);
		}
	}
}
