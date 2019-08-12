package TankGame.Loader;

import TankGame.GameObject.ResourceField;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static TankGame.GameObject.ResourceField.*;

public class SpriteLoader {
    private static final Logger LOGGER = Logger.getLogger(SpriteLoader.class.getName());
    private static Map<ResourceField, String> spritesMap = new HashMap<>();

    public SpriteLoader() {
        spritesMap.put(BACKGROUND, "src/TankGame/resources/Background.bmp");
        spritesMap.put(WALL, "src/TankGame/resources/Wall1.gif");
        spritesMap.put(BREAKABLE_WALL, "src/TankGame/resources/Wall2.gif");
        spritesMap.put(TANK1, "src/TankGame/resources/Tank1edit.gif");
        spritesMap.put(TANK2, "src/TankGame/resources/Tank2edit.gif");
        spritesMap.put(BULLET, "src/TankGame/resources/Shelledit2.gif");
    }

    public BufferedImage loadSprite(ResourceField field) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File(spritesMap.get(field)));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load sprite for " + field, e);
        }
        return sprite;
    }
}
