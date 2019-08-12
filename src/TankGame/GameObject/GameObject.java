package TankGame.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class GameObject implements Observer {
    protected int x, y, height, width;
    protected BufferedImage img;

    public GameObject(BufferedImage img, int x, int y){
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.height = img.getHeight(null);
        this.width = img.getWidth(null);
    }

    public boolean isCollision(GameObject target) {
        Rectangle selfRect = new Rectangle(x, y, width, height);
        Rectangle targetRect = new Rectangle(target.getX(), target.getY(), target.getWidth(), target.getHeight());
        return selfRect.intersects(targetRect);
    }

    public int getX(){ return x; }

    public int getY(){ return y; }

    public int getWidth(){ return this.width; }

    public int getHeight(){ return this.height; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    @Override
    public void update(Observable o, Object arg) {

    }
}
