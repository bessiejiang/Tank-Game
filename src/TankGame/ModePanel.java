package TankGame;

import TankGame.GameObject.ResourceField;
import TankGame.Loader.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ModePanel extends JPanel {
    private BufferedImage img;

    public ModePanel(SpriteLoader spriteLoader) {
        img = spriteLoader.loadSprite(ResourceField.MENU);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0,0, 852, 710, this);

    }
}
