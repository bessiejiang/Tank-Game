package GameObject;

import java.awt.*;

public class GameObject {
    protected int x,y,speed, height, width;
    protected Image img;

    public GameObject(Image img, int x, int y, int speed){
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.height = img.getHeight(null);
        this.width = img.getWidth(null);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setX(int x) {
        this.x = x;
    }

}
