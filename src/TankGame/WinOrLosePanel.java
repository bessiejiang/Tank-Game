package TankGame;

import TankGame.GameObject.ResourceField;
import TankGame.Loader.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WinOrLosePanel extends JPanel {

    private BufferedImage img;

    public WinOrLosePanel(SpriteLoader spriteLoader) {
        img = spriteLoader.loadSprite(ResourceField.WIN_OR_LOSE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0,0, 852, 710, this);

    }
}
