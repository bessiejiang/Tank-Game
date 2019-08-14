package TankGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ModePanel extends JPanel {
    private BufferedImage img;

    public ModePanel() {
        try {
            img = ImageIO.read(ModePanel.class.getResourceAsStream("/TankGame/resources/Menu.png"));
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
