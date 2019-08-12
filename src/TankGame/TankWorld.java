package TankGame;

import TankGame.GameObject.GameObservable;
import TankGame.GameObject.Moveable.Tank;
import TankGame.Loader.SoundLoader;
import TankGame.Loader.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TankWorld implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(TankWorld.class.getName());

    private GameObservable gameObs;
    private SpriteLoader spriteLoader;
    private SoundLoader soundLoader;
    private GamePanel gamePanel;
    private ModePanel modePanel;
    private PlayerManager playerManager;
    private KeyController keyController;

    public TankWorld() {
        gameObs = new GameObservable();
        spriteLoader = new SpriteLoader();
        soundLoader = new SoundLoader();
        playerManager = new PlayerManager(spriteLoader, gameObs);
        gamePanel = new GamePanel(playerManager, spriteLoader, gameObs);
        modePanel = new ModePanel();
        keyController = new KeyController(playerManager);
    }

    @Override
    public void run() {
        initViewPanels();
        try {
            while(true) {
                gameObs.setChanged();
                gameObs.notifyObservers();
                gamePanel.repaint();
                Thread.sleep(1000 / 144); // TODO replace with meaningful interval
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "The game failed with unexpected errors", e);
        }
    }

    public static void main(String args[]) {
        TankWorld tankworld = new TankWorld();
        Thread thread = new Thread(tankworld);
        thread.start();
    }

    private void initViewPanels() {
        JFrame frame = gamePanel.setupFrame();
        // Set up card layout that contains two view panels, the mode panel allows selecting whether to play against human or computer
        CardLayout cl = new CardLayout();
        JPanel container = new JPanel(cl);
        container.add(gamePanel, "game");
        container.add(modePanel, "mode");

        JButton humanVsHumanBtn = new JButton("Human vs Human");
        humanVsHumanBtn.setPreferredSize(new Dimension(500, 150));
        humanVsHumanBtn.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        humanVsHumanBtn.addActionListener(e -> {
            gamePanel.setOnePlayerMode(false);
            gamePanel.addKeyListener(keyController);
            cl.show(container, "game");
        });

        JButton humanVsComputerBtn = new JButton("Human vs Computer");
        humanVsComputerBtn.setPreferredSize(new Dimension(500, 150));
        humanVsComputerBtn.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        humanVsComputerBtn.addActionListener(e -> {
            gamePanel.setOnePlayerMode(true);
            Tank tank2 = playerManager.getPlayer2();
            tank2.setSpeed(tank2.getSpeed() - 1); // slow the enemy speed, making it easier to play
            gamePanel.addKeyListener(keyController);
            cl.show(container, "game");
        });

        modePanel.add(humanVsHumanBtn);
        modePanel.add(humanVsComputerBtn);

        frame.add(container);
        cl.show(container, "mode");

        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
    }
}
