import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.List;
import java.util.LinkedList;
import java.awt.Point;

class MonsterSprite{
    int x;
    int y;
    Circle circle;
    int scalingFactor;
    int radius = 5;

    MonsterSprite(int x, int y, int scalingFactor){
        this.x = x;
        this.y = y;
        circle= new Circle();
        setPositionX(x);
        setPositionY(y);
        circle.setRadius(radius);
        this.scalingFactor = scalingFactor;
    }

    Circle getCircle(){
        return circle;
    }



    void setX(int x){
        this.x = x;
        setPositionX(x);
    }

    void setY(int y){
        this.y = y;
        setPositionY(y);
    }

    int getX(){
        return x;
    }

    int getY(){
        return y;
    }

    public void setLineColor(Circle circle, Color color){
        circle.setStroke(color);
        circle.setFill(color);
    }

    public void setPositionX(int x){
        circle.setCenterX(x*scalingFactor + (scalingFactor/2));
    }

    public void setPositionY(int y){
        circle.setCenterY(y*scalingFactor + (scalingFactor/2));
    }
}

public class Monster implements Runnable {

    Boolean running = true;
    int radius;
    Random random = new Random();
    int scalingFactor;
    Point ship;
    boolean gameOver;

    public List<Point> monsterList = new LinkedList<>();

    MonsterSprite[] monsterSprites = new MonsterSprite[20];
    public Monster(int scalingFactor){
        for(int j = 0; j < 20; j++){
            int x = random.nextInt(30);
            int y = random.nextInt(30);
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

    @Override
    public void run() {


        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monsterList = new LinkedList<>();
            for (MonsterSprite monsterSprite : monsterSprites) {
                // Move X
                int xMove = monsterSprite.getX() + random.nextInt(3) - 1;
                if (xMove >= 0 && xMove <= 30)
                    monsterSprite.setX(xMove);
                // Move Y
                int yMove = monsterSprite.getY() + random.nextInt(3) - 1;
                if (yMove >= 0 && yMove <= 30)
                    monsterSprite.setY(yMove);

                monsterList.add(new Point(monsterSprite.getX(), monsterSprite.getY()));
                getShipPoint(ship);
            }
        }

    }
}
