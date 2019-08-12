package TankGame;

import TankGame.GameObject.Moveable.Tank;
import TankGame.GameObject.ResourceField;
import TankGame.Loader.SpriteLoader;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PlayerManager {
    private static final int TANK1_X = 150;
    private static final int TANK2_X = 1500;
    private static final int TANK1_Y = 700;
    private static final int TANK2_Y = 700;
    private static final int TANK_SPEED = 2;

    private Tank player1;
    private Tank player2;

    public PlayerManager(SpriteLoader spriteLoader) {
        BufferedImage tank1 = spriteLoader.loadSprite(ResourceField.TANK1);
        BufferedImage tank2 = spriteLoader.loadSprite(ResourceField.TANK2);

        player1 = new Tank(tank1, TANK1_X, TANK1_Y, TANK_SPEED,
                KeyEvent.VK_A, KeyEvent.VK_D,
                KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_SPACE);

        player2 = new Tank(tank2, TANK2_X, TANK2_Y, TANK_SPEED,
                KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER);

        // Let two tanks face each other
        player2.setAngle(180);
    }

    public Tank getPlayer1() {
        return player1;
    }

    public Tank getPlayer2() {
        return player2;
    }
}
