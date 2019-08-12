package TankGame.GameObject.Unmovable;

import TankGame.GameObject.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;

public class BreakableWall extends Unmovable {

    private int breakage = 0;

    public BreakableWall(int x, int y, BufferedImage img) {
        super(img, x, y);
    }

    public void draw(Graphics g, ImageObserver o){
        g.drawImage(img,x,y,o);
    }

    public int getBreakage(){
        return breakage;
    }

    @Override
    public void update(Observable o, Object arg) {
        super.update(o, arg);
    }
}
