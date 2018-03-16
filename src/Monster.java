import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import java.util.List;
import java.util.LinkedList;
import java.awt.Point;

/*
* Brennen Stenson wrote this file. Any subsequent changes made to it are the author of whoever is in the commit history.
* Git wasn't very friendly to Brennen. So, grader guys, just know that he wrote the majority of this file.
*
*
* */
public class Monster implements Runnable {

    Boolean running = true;
    int radius;
    Random random = new Random();
    int scalingFactor;
    Point ship;
    boolean gameOver = false;
    int mapDimension;
    int numMonsters = 12;

    public List<Point> monsterList = new LinkedList<>();

    MonsterSprite[] monsterSprites = new MonsterSprite[numMonsters];

    public Monster(int scalingFactor){
        mapDimension = Map.getInstance().getDimension();
        for(int j = 0; j < numMonsters; j++){
            int x = random.nextInt(mapDimension);
            int y = random.nextInt(mapDimension);
            monsterSprites[j] = new MonsterSprite(x,y,scalingFactor);
        }
        this.radius = 10;
        this.scalingFactor = scalingFactor;
    }

    public void addToPane(ObservableList<Node> sceneGraph){
        for(MonsterSprite monsterSprite: monsterSprites){

            Circle circle = monsterSprite.getCircle();
            System.out.println("Adding circle to pane: " + circle.getCenterX() + " " + circle.getCenterY() + " " + radius);
            sceneGraph.add(circle);
        }
    }

    public void getShipPoint(Point ship){
        this.ship = ship;
        for(Point monsterPoint : monsterList) {
            if (monsterPoint.x == ship.x && monsterPoint.y == ship.y) {
                gameOver = true;
            }
        }
    }

    private synchronized boolean shouldRun() {
        return !Main.stop;
    }

    @Override
    public void run() {


        while (shouldRun()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monsterList = new LinkedList<>();
            for (MonsterSprite monsterSprite : monsterSprites) {
                // Move X
                int xMove = monsterSprite.getX() + random.nextInt(3) - 1;
                if (xMove >= 0 && xMove <= mapDimension)
                    monsterSprite.setX(xMove);
                // Move Y
                int yMove = monsterSprite.getY() + random.nextInt(3) - 1;
                if (yMove >= 0 && yMove <= mapDimension)
                    monsterSprite.setY(yMove);

                monsterList.add(new Point(monsterSprite.getX(), monsterSprite.getY()));
                getShipPoint(ship);
            }
        }

    }
}
