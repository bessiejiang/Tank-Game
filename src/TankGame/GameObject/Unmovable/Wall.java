package TankGame.GameObject.Unmovable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends Unmovable {

    public Wall(int x, int y, BufferedImage img){
        super(img, x, y);
    }

    public void draw(Graphics2D g) {
        g.drawImage(img, x, y, null);
    }
}
