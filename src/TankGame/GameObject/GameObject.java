package TankGame.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class GameObject implements Observer {
    protected int x, y, height, width;
    protected Rectangle pos;
    protected BufferedImage img;

    public GameObject(BufferedImage img, int x, int y){
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.height = img.getHeight(null);
        this.width = img.getWidth(null);
        this.pos = new Rectangle(x, y, width, height);
    }

    public void setImage(BufferedImage img){
        this.img = img;
        height = img.getHeight();
        width = img.getWidth();
    }

    public int getX(){ return pos.x; }

    public int getY(){ return pos.y; }

    public int getWidth(){ return this.width; }

    public int getHeight(){ return this.height; }

    public void setX(int x) { pos.x = x; }

    public void setY(int y){ pos.y = y; }

    @Override
    public void update(Observable o, Object arg) {

    }
}
