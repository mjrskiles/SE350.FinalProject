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
  private Image pirateImage = new Image(getClass().getResource("pirateShip.png").toExternalForm(),
																	50, 50, true, true);
	private List<PirateShip> pirates = new LinkedList<PirateShip>();
	private List<ImageView>  pirateImageViews = new LinkedList<ImageView>();

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

				int counter = 0;
				for(ImageView pirateImageView : pirateImageViews) {
					PirateShip pirate = pirates.get(counter);
					pirateImageView.setX(pirate.getLocation().x * scalingFactor);
					pirateImageView.setY(pirate.getLocation().y * scalingFactor);
					counter++;
				}
		}
		});
	}

	private void setObservers() {
		for(PirateShip pirate : pirates) {
			ship.addObserver(pirate);
		}
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
		pirateImageView.setX(x * scalingFactor);
		pirateImageView.setY(y * scalingFactor);
		PirateShip pirate = new PirateShip(ship, map.getMap());
		pirate.setLocation(x, y);
		pirates.add(pirate);
		pirateImageViews.add(pirateImageView);

	}

	/*
	* Because of view order, the pirates need to be added after the rectangles
	* have been. Otherwise they won't show up.
	*/
	private void addPirates(AnchorPane root) {
		for(ImageView pirateImageView : pirateImageViews) {
			root.getChildren().add(pirateImageView);
		}
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

				if(map[y][x] == CellTypes.ocean()) {
					rect.setFill(Color.PALETURQUOISE);
					root.getChildren().add(rect);
				}
				else if(map[y][x] == CellTypes.island()) {
					// rect.setFill(Color.FORESTGREEN);
					addIslandImage(root, x, y);
				}
				else { // is a pirate cell
					// create ocean tile anyways
					// the tile will be "underneath" the pirate since it is added to the tree later
					rect.setFill(Color.PALETURQUOISE);
					root.getChildren().add(rect);
					createPirate(root, x, y);
					// Because navigation relies on cell types
					map[y][x] = CellTypes.ocean();
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
			drawMap(map.getMap(), root); // everything else is rendered lower than pirate
			addPirates(root); // just below ship's Z-index
			loadShipImage(root); // highest level Z-index
			primaryStage.show();
			startSailing(scene);
			setObservers(); // set the observers after events have been registered
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
