package TankGame;

import TankGame.GameObject.GameObservable;
import TankGame.GameObject.Moveable.Tank;
import TankGame.GameObject.ResourceField;
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
    private WinOrLosePanel winOrLosePanel;
    private PlayerManager playerManager;
    private KeyController keyController;

    public TankWorld() {
        gameObs = new GameObservable();
        spriteLoader = new SpriteLoader();
        soundLoader = new SoundLoader();
        playerManager = new PlayerManager(spriteLoader, gameObs);
        gamePanel = new GamePanel(playerManager, spriteLoader, gameObs);
        modePanel = new ModePanel();
        winOrLosePanel = new WinOrLosePanel();
        modePanel.setLayout(null);
        keyController = new KeyController(playerManager);
    }

    @Override
    public void run() {
        soundLoader.playSound(ResourceField.MUSIC, true);
        initViewPanels();
        try {
            while(true) {
                gameObs.setChanged();
                gameObs.notifyObservers();
                gamePanel.repaint();
                Thread.sleep(7);
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
        container.add(winOrLosePanel, "winOrLose");

        JButton humanVsHumanBtn = new JButton("Human vs Human");
        humanVsHumanBtn.setBounds(270, 420, 350,50);
        humanVsHumanBtn.setOpaque(false);
        humanVsHumanBtn.setContentAreaFilled(false);
        humanVsHumanBtn.setBorderPainted(false);
        humanVsHumanBtn.setFont(new Font("Courier", Font.BOLD, 32));
        humanVsHumanBtn.addActionListener(e -> {
            gamePanel.setOnePlayerMode(false);
            gamePanel.addKeyListener(keyController);
            cl.show(container, "game");
        });

        JButton humanVsComputerBtn = new JButton("Human vs Computer");
        humanVsComputerBtn.setBounds(275, 480, 350,50);
        humanVsComputerBtn.setOpaque(false);
        humanVsComputerBtn.setContentAreaFilled(false);
        humanVsComputerBtn.setBorderPainted(false);
        humanVsComputerBtn.setFont(new Font("Courier", Font.BOLD, 30));
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
