package TankGame;

import TankGame.GameObject.Moveable.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {
    private final PlayerManager playerManager;

    public KeyController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        Tank tank1 = playerManager.getPlayer1();
        Tank tank2 = playerManager.getPlayer2();
        int key = e.getKeyCode();

        // Key control for tank1
        if (key == KeyEvent.VK_W) {
            tank1.switchUpOn();
        }
        if (key == KeyEvent.VK_S) {
            tank1.switchDownOn();
        }
        if (key == KeyEvent.VK_A) {
            tank1.switchLeftOn();
        }
        if (key == KeyEvent.VK_D) {
            tank1.switchRightOn();
        }
        if (key == KeyEvent.VK_SPACE) {
            tank1.switchShootOn();
        }

        // Key control for tank2
        if (key == KeyEvent.VK_UP) {
            tank2.switchUpOn();
        }
        if (key == KeyEvent.VK_DOWN) {
            tank2.switchDownOn();
        }
        if (key == KeyEvent.VK_LEFT) {
            tank2.switchLeftOn();
        }
        if (key == KeyEvent.VK_RIGHT) {
            tank2.switchRightOn();
        }
        if (key == KeyEvent.VK_ENTER) {
            tank2.switchShootOn();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        Tank tank1 = playerManager.getPlayer1();
        Tank tank2 = playerManager.getPlayer2();
        int key = e.getKeyCode();

        // Key control for tank1
        if (key == KeyEvent.VK_W) {
            tank1.switchUpOff();
        }
        if (key == KeyEvent.VK_S) {
            tank1.switchDownOff();
        }
        if (key == KeyEvent.VK_A) {
            tank1.switchLeftOff();
        }
        if (key == KeyEvent.VK_D) {
            tank1.switchRightOff();
        }
        if (key == KeyEvent.VK_SPACE) {
            tank1.switchShootOff();
        }

        // Key control for tank2
        if (key == KeyEvent.VK_UP) {
            tank2.switchUpOff();
        }
        if (key == KeyEvent.VK_DOWN) {
            tank2.switchDownOff();
        }
        if (key == KeyEvent.VK_LEFT) {
            tank2.switchLeftOff();
        }
        if (key == KeyEvent.VK_RIGHT) {
            tank2.switchRightOff();
        }
        if (key == KeyEvent.VK_ENTER) {
            tank2.switchShootOff();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
