package TankGame.GameObject.Moveable;

import TankGame.GameObject.GameObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class Tank extends Movable {

    private int left, right, up, down, shoot;
    private int angle;
    private boolean moveLeft, moveRight, moveUp, moveDown, shootable;

    public Tank(BufferedImage img, int x, int y, int speed, int left, int right, int up, int down, int shoot){
        super(img, x, y, speed);
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.shoot = shoot;
        this.angle = 0;
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.shootable = false;
    }

    public void turn(int angle){
        this.angle += this.angle;
        this.angle = this.angle %360;
        if (this.angle < 0) {
            this.angle = angle + 360;
        }
    }

    public boolean isCollision(GameObject go) {
        Rectangle pos2 = new Rectangle(go.getX(), go.getY(), go.getWidth(), go.getHeight());
        return pos.intersects(pos2);
    }

    public void draw(Graphics2D g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), img.getWidth(null) / 2, img.getHeight(null) / 2);
        g.drawImage(img, rotation, null);
    }

    @Override
    public void update(Observable o, Object arg) {

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
