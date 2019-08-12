package TankGame.GameObject.Moveable;

import TankGame.GameObject.GameObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class Tank extends Movable {

    private int angle;
    private int turnSpeed;
    private boolean moveLeft, moveRight, moveUp, moveDown, shootable;

    public Tank(BufferedImage img, int x, int y, int speed, int turnSpeed, Observable gameObs){
        super(img, x, y, speed);
        this.turnSpeed = turnSpeed;
        this.angle = 0;
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.shootable = false;
        gameObs.addObserver(this);
    }

    public boolean isCollision(GameObject gameObject) {
        Rectangle tankRect = new Rectangle(x, y, width, height);
        Rectangle gameObjRect = new Rectangle(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
        return tankRect.intersects(gameObjRect);
    }

    public void draw(Graphics2D g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), img.getWidth(null) / 2, img.getHeight(null) / 2);
        g.drawImage(img, rotation, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (moveLeft){
            angle -= turnSpeed;
        }
        if (moveRight){
            angle += turnSpeed;
        }
        if (moveUp){
            x = ((int) (x + Math.round(speed * Math.cos(Math.toRadians(angle)))));
            y = ((int) (y + Math.round(speed * Math.sin(Math.toRadians(angle)))));
        }
        if (moveDown){
            x = ((int) (x - Math.round(speed * Math.cos(Math.toRadians(angle)))));
            y = ((int) (y - Math.round(speed * Math.sin(Math.toRadians(angle)))));
        }
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
}
