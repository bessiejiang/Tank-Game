package TankGame.GameObject.Moveable;

import TankGame.GameObject.ResourceField;
import TankGame.Loader.SpriteLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Tank extends Movable {
    private static int FIRE_COOL_DOWN = 75;
    private static int LIFE_POINT = 100;

    private int angle;
    private int turnSpeed;
    private int fireCoolDown;
    private int lifePoint;
    private boolean moveLeft, moveRight, moveUp, moveDown, shootable;
    private SpriteLoader spriteLoader;
    private Observable gameObs;
    private List<Bullet> bullets;
    private Tank rivalTank;

    public Tank(BufferedImage img, int x, int y, int speed, int turnSpeed, Observable gameObs, SpriteLoader spriteLoader){
        super(img, x, y, speed);
        this.turnSpeed = turnSpeed;
        this.angle = 0;
        this.fireCoolDown = 0;
        this.lifePoint = LIFE_POINT;
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.shootable = false;
        this.gameObs = gameObs;
        this.spriteLoader = spriteLoader;
        this.bullets = new ArrayList<>();
        gameObs.addObserver(this);
    }

    public void draw(Graphics2D g) {
        if (lifePoint > 0) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle), img.getWidth(null) / 2, img.getHeight(null) / 2);
            g.drawImage(img, rotation, null);
        } else {

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (fireCoolDown != 0) {
            fireCoolDown--;
        }

        if (lifePoint > 0) {
            if (shootable && fireCoolDown == 0) {
                Bullet bullet = new Bullet(spriteLoader.loadSprite(ResourceField.BULLET), getTankCenterX(), getTankCenterY(), angle);
                bullets.add(bullet);
                gameObs.addObserver(bullet);
                fireCoolDown = FIRE_COOL_DOWN; //restart the cooling down
            }

            handleCollisionWithBullets();

            if (moveLeft) {
                angle -= turnSpeed;
            }
            if (moveRight) {
                angle += turnSpeed;
            }
            if (moveUp) {
                x = ((int) (x + Math.round(speed * Math.cos(Math.toRadians(angle)))));
                y = ((int) (y + Math.round(speed * Math.sin(Math.toRadians(angle)))));
            }
            if (moveDown) {
                x = ((int) (x - Math.round(speed * Math.cos(Math.toRadians(angle)))));
                y = ((int) (y - Math.round(speed * Math.sin(Math.toRadians(angle)))));
            }
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return this.angle;
    }

    public void switchUpOn() {
        this.moveUp = true;
    }

    public void switchDownOn() {
        this.moveDown = true;
    }

    public void switchLeftOn() {
        this.moveLeft = true;
    }

    public void switchRightOn() {
        this.moveRight = true;
    }

    public void switchUpOff() {
        this.moveUp = false;
    }

    public void switchDownOff() {
        this.moveDown = false;
    }

    public void switchLeftOff() {
        this.moveLeft = false;
    }

    public void switchRightOff() {
        this.moveRight = false;
    }

    public void switchShootOn() {
        this.shootable = true;
    }

    public void switchShootOff() {
        this.shootable = false;
    }

    public int getTankCenterX() {
        return x + img.getWidth(null) / 2;
    }

    public int getTankCenterY() {
        return y + img.getHeight(null) / 2;
    }

    public void setRivalTank(Tank rivalTank) {
        this.rivalTank = rivalTank;
    }

    private void handleCollisionWithBullets() {
        for (Bullet bullet: rivalTank.getBullets()) {
            if (bullet.isVisible() && isCollision(bullet)) {
                lifePoint -= Bullet.POWER;
                bullet.setVisible(false);
            }
        }
    }
}
