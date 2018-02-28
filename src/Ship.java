
import java.awt.Point;
import java.util.Observable;

public class Ship extends Observable {
	private Point position;
	private Map map;
	
	public Ship(Map map) {
		position = new Point(5, 5);
		this.map = map;
	}
	
	public Point getLocation() {
		return position;
	}
	
	public void goEast() {
		// go right
		final int[][] grid  = map.getMap();
    final int bounds = grid[0].length;
                
		if(position.x + 1 < bounds  && grid[position.y][position.x + 1] == CellTypes.ocean()) {
			position.move(position.x + 1, position.y);
		}
	}
	
	public void goWest() {
		// go left
    final int[][] grid = map.getMap();
    final int bounds = grid[0].length;

		if(position.x - 1 >= 0 && grid[position.y][position.x - 1] == CellTypes.ocean()) {
			position.move(position.x - 1, position.y);
		}
	}
	
	public void goNorth() {

	  final int[][] grid = map.getMap();

		if(position.y - 1 >= 0 && grid[position.y - 1][position.x] == CellTypes.ocean()) {
			position.move(position.x, position.y - 1);
		}
	}
	
	public void goSouth() {
            
	  final int[][]grid = map.getMap();

		if(position.y + 1 < bounds  && grid[position.y + 1][position.x] == CellTypes.ocean()) {
			position.move(position.x, position.y + 1);
		}
	}
}
