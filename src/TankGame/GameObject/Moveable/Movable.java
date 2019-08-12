package TankGame.GameObject.Moveable;

import TankGame.GameObject.GameObject;

import java.awt.image.BufferedImage;

public class Movable extends GameObject {
    protected int speed;

    public Movable(BufferedImage img, int x, int y, int speed) {
        super(img, x,y);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
