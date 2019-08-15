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
    private CardLayout cardLayout;
    private JPanel cardContainer;
    private GamePanel gamePanel;
    private ModePanel modePanel;
    private WinOrLosePanel winOrLosePanel;
    private PlayerManager playerManager;
    private KeyController keyController;
    private boolean isRunning;

    public TankWorld() {
        gameObs = new GameObservable();
        spriteLoader = new SpriteLoader();
        soundLoader = new SoundLoader();
        playerManager = new PlayerManager(spriteLoader, gameObs);
        cardLayout = new CardLayout();
        cardContainer = new JPanel(cardLayout);
        gamePanel = new GamePanel(playerManager, spriteLoader, gameObs);
        modePanel = new ModePanel();
        winOrLosePanel = new WinOrLosePanel();
        modePanel.setLayout(null);
        keyController = new KeyController(playerManager);
        isRunning = true;
    }

    @Override
    public void run() {
        soundLoader.playSound(ResourceField.MUSIC, true);
        initViewPanels();
        try {
            while(isRunning) {
                checkWinner();
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

    private void checkWinner() {
        Tank tank1 = playerManager.getPlayer1();
        Tank tank2 = playerManager.getPlayer2();

        if (tank1.getLifeCount() == 0 || tank2.getLifeCount() == 0) {
            isRunning = false;
            JLabel winner = new JLabel();;
            winner.setText(tank1.getLifeCount() == 0 ? "Player 2 wins!" : "Player 1 wins!");
            winner.setFont(new Font("Courier", Font.BOLD, 32));
            winOrLosePanel.setLayout(new GridBagLayout());
            winOrLosePanel.add(winner);
            cardLayout.show(cardContainer, "winOrLose");
        }
    }

    private void initViewPanels() {
        JFrame frame = gamePanel.setupFrame();
        // Set up card layout that contains three view panels, the mode panel allows selecting whether to play against human or computer;
        // the game panel has the game layout; the winOrLose panel has the game over layout.
        cardContainer.add(gamePanel, "game");
        cardContainer.add(modePanel, "mode");
        cardContainer.add(winOrLosePanel, "winOrLose");

        JButton humanVsHumanBtn = new JButton("Human vs Human");
        humanVsHumanBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        humanVsHumanBtn.setBounds(270, 420, 350,50);
        humanVsHumanBtn.setOpaque(false);
        humanVsHumanBtn.setContentAreaFilled(false);
        humanVsHumanBtn.setBorderPainted(false);
        humanVsHumanBtn.setFont(new Font("Courier", Font.BOLD, 32));
        humanVsHumanBtn.addActionListener(e -> {
            gamePanel.setOnePlayerMode(false);
            gamePanel.addKeyListener(keyController);
            cardLayout.show(cardContainer, "game");
        });

        JButton humanVsComputerBtn = new JButton("Human vs Computer");
        humanVsComputerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            cardLayout.show(cardContainer, "game");
        });

        modePanel.add(humanVsHumanBtn);
        modePanel.add(humanVsComputerBtn);

        frame.add(cardContainer);
        cardLayout.show(cardContainer, "mode");

        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
    }
}
