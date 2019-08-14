package TankGame.GameObject.Unmovable;

import TankGame.GameObject.Moveable.Tank;
import TankGame.PlayerManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class PowerUp extends Unmovable{

    boolean picked = false;
    private final PlayerManager playerManager;

    public PowerUp(BufferedImage img, int x, int y, PlayerManager playerManager, Observable gameObs) {
        super(img, x, y);
        gameObs.addObserver(this);
        this.playerManager = playerManager;
    }

    public void draw(Graphics2D g){
        if(!picked) {
            g.drawImage(this.img, this.x, this.y,null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Tank p1 = playerManager.getPlayer1();
        Tank p2 = playerManager.getPlayer2();

        handleCollisionWithTank(p1, p2);
    }

    private void handleCollisionWithTank(Tank p1, Tank p2) {

    }
}
