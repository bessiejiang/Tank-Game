package TankGame.GameObject.Moveable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class Bullet extends Movable {
    private static final int SPEED = 5;
    private int angle;
    private boolean isVisible;
    private int damage;

    public Bullet(BufferedImage img, int x, int y, int angle, int damage) {
        super(img, x, y, SPEED);
        this.angle = angle;
        this.isVisible = true;
        this.damage = damage;
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
            y += Math.round(SPEED * Math.sin(Math.toRadians(angle)));
            x += Math.round(SPEED * Math.cos(Math.toRadians(angle)));
        }
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public int getDamage() {
        return damage;
    }

}
