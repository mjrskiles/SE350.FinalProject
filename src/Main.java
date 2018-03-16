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


import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;


public class Main extends Application {
	private final int dimension = Map.getInstance().getDimension();
	static final int scalingFactor = 35; //This field determines the visual size of the cells.
	protected static Map map = Map.getInstance();
	private Ship ship = new Ship(map);
	private ImageView shipImageView;
	private Image islandImage = new Image("file:src/island.jpg",scalingFactor, scalingFactor, true, true);
	private Image treasureImage = new Image("file:src/treasure.jpeg",scalingFactor,scalingFactor,true,true);
    private Image rumImage = new Image("file:src/rum.png",scalingFactor, scalingFactor, true, true);
    private Image win = new Image("file:src/win.png",dimension*scalingFactor,dimension*scalingFactor,true,true);
    private Image lose = new Image("file:src/lose.png",dimension*scalingFactor,dimension*scalingFactor,true,true);
    private Image lose1 = new Image("file:src/lose1.png",dimension*scalingFactor,dimension*scalingFactor,true,true);
	private List<PirateShip> pirates = new LinkedList<PirateShip>();
	private PirateShipFactoryGenerator factoryGenerator = new PirateShipFactoryGenerator();
	private PirateShipFactory pirateFactory = new AveragePirateShipFactory();
	protected static boolean stop = false;
	private AnchorPane root;
	Monster monster;
	Thread monstersThread;

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
				monster.getShipPoint(ship.getLocation());
				if (monster.gameOver) checkMonster();

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

	private void checkMonster() {
		stop = true;
		addMonsterLoseImage(root);
		System.out.println("You've been attacked by a monster! You lose!");
	}

	private void addLoseImage(AnchorPane root) {
		ImageView loseImageView = new ImageView(lose);
		loseImageView.setX(0);
		loseImageView.setY(0);
		root.getChildren().add(loseImageView);
		
	}

	private void addMonsterLoseImage(AnchorPane root) {
		ImageView loseImageView = new ImageView(lose1);
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
				scalingFactor, scalingFactor, true, true);
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

    private void addRumImage(AnchorPane root, int x, int y) {
        ImageView rumImageView = new ImageView(rumImage);
        rumImageView.setX(x*scalingFactor);
        rumImageView.setY(y*scalingFactor);
        root.getChildren().add(rumImageView);
    }

	private void createPirates() {
        for(int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (map.getMap()[y][x] == CellTypes.pirate) {
                    pirateFactory = factoryGenerator.getRandomFactory();
                    PirateShip pirate = pirateFactory.createPirateShip(ship, x, y);
                    pirates.add(pirate);
                }
            }
        }
	}

	/*
	* Because of view order, the pirates need to be added after the rectangles
	* have been. Otherwise they won't show up.
	*/
	private void addPirates(AnchorPane root) {
		for(PirateShip pirate : pirates) {
			root.getChildren().add(pirate.getImageView());
//			System.out.println("Pirate added");
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

				int cell = map[y][x];
				switch (cell) {
                    case CellTypes.island:
                        addIslandImage(root, x, y);
                        break;
                    case CellTypes.pirate:
                        // create ocean tile anyways
                        // the tile will be "underneath" the pirate since it is added to the tree later
                        rect.setFill(Color.PALETURQUOISE);
                        root.getChildren().add(rect);
                        break;
                    case CellTypes.treasure:
                        addTreasureImage(root,x,y);
                        break;
                    case CellTypes.rum:
                        //Draw ocean behind the rum bottle
                        rect.setFill(Color.PALETURQUOISE);
                        root.getChildren().add(rect);

                        addRumImage(root, x, y);
                        break;

                    default:  //Default case is ocean
                    case CellTypes.ocean:
                        rect.setFill(Color.PALETURQUOISE);
                        root.getChildren().add(rect);
                        break;
                }
			}
		}
	}

	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new AnchorPane();
			Scene scene = new Scene(root, dimension*scalingFactor, dimension*scalingFactor);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ocean");
			drawMap(map.getMap(), root); // everything else is rendered before pirate
            createPirates();
			addPirates(root); // just below ship's Z-index
			loadShipImage(root); // highest level Z-index

			monster = new Monster(scalingFactor);
			monster.addToPane(root.getChildren());
			primaryStage.show();

			monstersThread = new Thread(monster);
			monstersThread.start();
			monster.getShipPoint(ship.getLocation());
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
