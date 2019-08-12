package TankGame.GameObject.Unmovable;

import TankGame.GameObject.Moveable.Bullet;
import TankGame.GameObject.Moveable.Tank;
import TankGame.PlayerManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class Wall extends Unmovable {
    private final PlayerManager playerManager;

    public Wall(int x, int y, BufferedImage img, PlayerManager playerManager, Observable gameObs){
        super(img, x, y);
        this.playerManager = playerManager;
        gameObs.addObserver(this);
    }

    public void draw(Graphics2D g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        Tank p1 = playerManager.getPlayer1();
        Tank p2 = playerManager.getPlayer2();

        handleCollisionWithTank(p1, p2);
        handleCollisionWithBullets(p1, p2);
    }

    private void handleCollisionWithBullets(Tank p1, Tank p2) {
        for (Bullet bullet: p1.getBullets()) {
            if (isCollision(bullet)) {
                bullet.setVisible(false);
            }
        }

        for (Bullet bullet: p2.getBullets()) {
            if (isCollision(bullet)) {
                bullet.setVisible(false);
            }
        }
    }

    private void handleCollisionWithTank(Tank p1, Tank p2) {
        if (p1.isCollision(this)) {
            if (p1.getX() > getX()) {
                p1.setX(p1.getX() + p1.getSpeed() + 1); // set back (speed + 1) distance that tank can be "bounced" back from wall
            } else if (p1.getX() < (getX())) {
                p1.setX(p1.getX() - p1.getSpeed() - 1);
            }
            if (p1.getY() > getY()) {
                p1.setY(p1.getY() + p1.getSpeed() + 1);
            } else if (p1.getY() < getY()) {
                p1.setY(p1.getY() - p1.getSpeed() - 1);
            }
        }
        if (p2.isCollision(this)) {
            if (p2.getX() > getX()) {
                p2.setX(p2.getX() + p2.getSpeed() + 1);
            } else if (p2.getX() < (getX())) {
                p2.setX(p2.getX() - p2.getSpeed() - 1);
            }
            if (p2.getY() > getY()) {
                p2.setY(p2.getY() + p2.getSpeed() + 1);
            } else if (p2.getY() < getY()) {
                p2.setY(p2.getY() - p2.getSpeed() - 1);
            }
        }
    }
}
