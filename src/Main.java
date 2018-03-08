import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.LinkedList;


public class Main extends Application {
	private final int dimension = 10;
	private final int scalingFactor = 50;
	private Map map = Map.getInstance();
	private Ship ship = new Ship(map);
	private ImageView shipImageView;
	private Image islandImage = new Image(getClass().getResource("island.jpg").toExternalForm(),50, 50, true, true);
	private Image treasureImage = new Image(getClass().getResource("treasure.jpeg").toExternalForm(),50,50,true,true);
	private Image win = new Image(getClass().getResource("win.png").toExternalForm(),500,500,true,true);
	private Image lose = new Image(getClass().getResource("lose.png").toExternalForm(),500,500,true,true);
	private List<PirateShip> pirates = new LinkedList<PirateShip>();
	private PirateShipFactory pirateFactory = new AveragePirateShipFactory(ship, map);
	private boolean stop = false;
	private AnchorPane root;
	

	private void startSailing(Scene scene) {
		scene.setOnKeyPressed((e) -> {
			if (stop == false){
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
				checkTreasure();
				checkPirate();
			}

		});

	}

	private void checkTreasure() {
		if (ship.hasTreasure == true){ 
			stop = true;
			addWinImage(root);
			System.out.println("You found the treasure! You win!");
		}

	}
	
	private void addWinImage(AnchorPane root) {
		ImageView winImageView = new ImageView(win);
		winImageView.setX(0);
		winImageView.setY(0);
		root.getChildren().add(winImageView);
		
	}
	private void checkPirate() {
		if (ship.hitPirate == true){
			stop = true;
			addLoseImage(root);
			System.out.println("You've been caught by a pirate! You lose!");
		}
	}

	private void addLoseImage(AnchorPane root) {
		ImageView loseImageView = new ImageView(lose);
		loseImageView.setX(0);
		loseImageView.setY(0);
		root.getChildren().add(loseImageView);
		
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
  
  private void addTreasureImage(AnchorPane root, int x, int y) {
		ImageView treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(x*scalingFactor);
		treasureImageView.setY(y*scalingFactor);
		root.getChildren().add(treasureImageView);
		
	}

	private void createPirate(AnchorPane root, int x, int y) {
		PirateShip pirate = pirateFactory.createPirateShip(x, y);
		pirates.add(pirate);
	}

	/*
	* Because of view order, the pirates need to be added after the rectangles
	* have been. Otherwise they won't show up.
	*/
	private void addPirates(AnchorPane root) {
		for(PirateShip pirate : pirates) {
			root.getChildren().add(pirate.getImageView());
			System.out.println("Pirate added");
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
				else if(map[y][x] == CellTypes.treasure()) {
					addTreasureImage(root,x,y);
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
			pirateFactory.setImageFromPath("pirateShip.png");
			root = new AnchorPane();
			Scene scene = new Scene(root,500,500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ocean");
			drawMap(map.getMap(), root); // everything else is rendered before pirate
			addPirates(root); // just below ship's Z-index
			loadShipImage(root); // highest level Z-index
			primaryStage.show();
			startSailing(scene);
			setObservers(); // set the observers after events have been registered
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setFactory(PirateShipFactory factory) {
		pirateFactory = factory;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
