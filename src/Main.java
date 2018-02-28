
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.util.List;
import java.util.LinkedList;
import java.awt.Point;


public class Main extends Application {
	final int dimension = 10;
	final int scalingFactor = 50;
	Map map = new Map();
	Ship ship = new Ship(map);
	private ImageView shipImageView;
	private Image islandImage = new Image(getClass().getResource("island.jpg").toExternalForm(),
																	50, 50, true, true);
  private Image pirateImage = new Image(getClass().getResource("pirate.jpg").toExternalForm(),
																	50, 50, true, true);
	private List<PirateShip> pirates = new LinkedList<PirateShip>();
	
	private void startSailing(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
					case RIGHT:
						ship.goEast();
						break;
					case LEFT:
						ship.goWest();
						break;
					case DOWN:
						ship.goSouth();
						break;
					case UP:
						ship.goNorth();
						break;
					default:
						break;
				}
				shipImageView.setX(ship.getLocation().x * scalingFactor);
				shipImageView.setY(ship.getLocation().y * scalingFactor);
		}
		});
	}
	
		private void loadShipImage(AnchorPane root) {
			Image shipImage = new Image(getClass().getResource("ship.png").toExternalForm(),
					50, 50, true, true);
			shipImageView = new ImageView(shipImage);
			shipImageView.setX(ship.getLocation().x * scalingFactor);
			shipImageView.setY(ship.getLocation().y * scalingFactor);
			root.getChildren().add(shipImageView);
		}

    private void addIslandImage(AnchorPane root, int x, int y) {
        ImageView islandImageView = new ImageView(islandImage);
        islandImageView.setX(x * scalingFactor);
        islandImageView.setY(y * scalingFactor);
        root.getChildren().add(islandImageView);
    }
		
		private void createPirate(AnchorPane root, int x, int y) {
			ImageView pirateImageView = new ImageView(pirateImage);
			
		}
	
	private void drawMap(int[][] map, AnchorPane root) {
		for(int y = 0; y < dimension; y++) {
			for(int x = 0; x < dimension; x++) {
				int X = x * scalingFactor;
				int Y = y * scalingFactor;
				int W = scalingFactor;
				int H = W;
				Rectangle rect = new Rectangle(X, Y, W, H);
				rect.setStroke(Color.BLACK);
				
				if(map[y][x] == 0) {
					rect.setFill(Color.PALETURQUOISE);
					root.getChildren().add(rect);
				}
				
				else if(map[y][x] == 1) {
					// rect.setFill(Color.FORESTGREEN);
					addIslandImage(root, x, y);
				}
				
				else {
					
				}
			}
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root,500,500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ocean");
			drawMap(map.getMap(), root);
			loadShipImage(root);
			primaryStage.show();
			startSailing(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
