package TankGame.GameObject.Unmovable;

import TankGame.GameObject.GameObject;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class Unmovable extends GameObject {

    public Unmovable(BufferedImage img, int x, int y) {
        super(img, x,y);
    }

    public void start(Observable g){
        g.addObserver(this);
    }

}
