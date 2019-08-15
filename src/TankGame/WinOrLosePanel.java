package TankGame;

import TankGame.GameEvent.WinOrLose;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WinOrLosePanel extends JPanel {

    private BufferedImage img;

    public WinOrLosePanel() {
        try {
            img = ImageIO.read(WinOrLosePanel.class.getResourceAsStream("/TankGame/resources/WinOrLose.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0,0, 852, 710, this);

    }
}
