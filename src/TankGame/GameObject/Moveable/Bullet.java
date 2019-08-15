package TankGame.GameObject.Moveable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class Bullet extends Movable {
    public static int power = 10;
    private static int speed = 5;
    private int angle;
    private boolean isVisible;

    public Bullet(BufferedImage img, int x, int y, int angle) {
        super(img, x, y, speed);
        this.angle = angle;
        this.isVisible = true;
    }

    public void draw(Graphics2D g) {
        if (isVisible) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle), 0, 0);
            g.drawImage(img, rotation, null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (isVisible) {
            y += Math.round(speed * Math.sin(Math.toRadians(angle)));
            x += Math.round(speed * Math.cos(Math.toRadians(angle)));
        }
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void setPower(int power){ this.power = power; }

    public boolean isVisible() {
        return isVisible;
    }

}
