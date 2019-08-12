package TankGame.GameObject.Moveable;

import TankGame.GameObject.GameObject;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class Movable extends GameObject {
    private int speed;

    public Movable(BufferedImage img, int x, int y, int speed) {
        super(img, x,y);
        this.speed = speed;
    }

    public void start(Observable g){
        g.addObserver(this);
    }

}
