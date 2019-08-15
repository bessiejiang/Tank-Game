package TankGame.GameObject.Unmovable;

import TankGame.GameObject.Moveable.Bullet;
import TankGame.GameObject.Moveable.Tank;
import TankGame.PlayerManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class BreakableWall extends Wall {

    private boolean isBroken = false;

    public BreakableWall(int x, int y, BufferedImage img, PlayerManager playerManager, Observable gameObs){
        super(img, x, y, playerManager);
        gameObs.addObserver(this);
    }

    public void draw(Graphics2D g) {
        if(!isBroken) {
            g.drawImage(img, x, y, null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!isBroken) {
            Tank p1 = playerManager.getPlayer1();
            Tank p2 = playerManager.getPlayer2();
            handleCollisionWithTank(p1, p2);
            handleCollisionWithBullets(p1, p2);
        }
    }

    private void handleCollisionWithBullets(Tank p1, Tank p2) {
        int breakage = 0;
        for (Bullet bullet : p1.getBullets()) {
            if (isCollision(bullet)) {
                breakage++;
                if(breakage >= 2){
                    isBroken = true;
                }
                bullet.setVisible(false);
            }
        }
        breakage = 0;
        for (Bullet bullet : p2.getBullets()) {
            if (isCollision(bullet)) {
                breakage++;
                if(breakage >= 2){
                    isBroken = true;
                }
                bullet.setVisible(false);
            }
        }
    }

}
