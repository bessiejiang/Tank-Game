package TankGame.GameObject.Moveable;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class Bullet extends Movable {
    public Bullet(BufferedImage img, int x, int y, int speed) {
        super(img, x, y, speed);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
