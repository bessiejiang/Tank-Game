package TankGame;

import TankGame.GameObject.Moveable.Tank;
import TankGame.GameObject.ResourceField;
import TankGame.GameObject.Unmovable.Wall;
import TankGame.Loader.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    // width: 52    height: 44
    private static final int[][] LAYOUT = new int[][]{
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,1,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,1,1,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,1,1,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,1},
        {1,0,0,0,0,1,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,1,1,1,0,0,0,0,1},
        {1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
        {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
        {1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
        {1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1},
        {1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private static final int MAP_WIDTH = LAYOUT[0].length * 32; // 52 * 32
    private static final int MAP_HEIGHT = LAYOUT.length * 32; // 44 * 32
    private static final int FRAME_WIDTH = MAP_WIDTH / 2;
    private static final int FRAME_HEIGHT = MAP_HEIGHT / 2;
    private static final int MINI_MAP_WIDTH = MAP_WIDTH / 8;
    private static final int MINI_MAP_HEIGHT = MAP_HEIGHT / 8;
    private static final int WALL_SIZE = 32;

    private boolean isOnePlayerMode;
    private PlayerManager playerManager;
    private SpriteLoader spriteLoader;
    private List<Wall> wallList;

    public GamePanel(PlayerManager playerManager, SpriteLoader spriteLoader) {
        super();
        this.playerManager = playerManager;
        this.spriteLoader = spriteLoader;

        isOnePlayerMode = false;
        wallList = createWallList();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isOnePlayerMode) {
            runEnemyAI();
        }

        drawGameMap(g);
    }

    JFrame setupFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Tank Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    void setOnePlayerMode(boolean isOnePlayerMode) {
        this.isOnePlayerMode = isOnePlayerMode;
    }

    private void drawGameMap(Graphics g) {
        Tank tank1 = playerManager.getPlayer1();
        Tank tank2 = playerManager.getPlayer2();

        BufferedImage gameMap = new BufferedImage(MAP_WIDTH, MAP_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = gameMap.createGraphics();

        // draw sprites and decorate on the game buffered image
        drawBackground(g2);
        drawWall(g2);
        drawTanks(g2);

        // create split windows on the game buffered image
        BufferedImage window1 = gameMap.getSubimage(getWindowX(tank1), getWindowY(tank1), FRAME_WIDTH / 2, FRAME_HEIGHT);
        BufferedImage window2 = gameMap.getSubimage(getWindowX(tank2), getWindowY(tank2), FRAME_WIDTH / 2, FRAME_HEIGHT);
        Image mini_window = gameMap.getScaledInstance(MINI_MAP_WIDTH, MINI_MAP_HEIGHT, Image.SCALE_SMOOTH);

        // Paint the split windows
        g.drawImage(window1, 0, 0, this);
        g.drawImage(window2, FRAME_WIDTH / 2, 0, this);
        g.drawImage(mini_window, 0, FRAME_HEIGHT - MINI_MAP_HEIGHT - 20, this);

        // borders
//        g.setColor(Color.BLACK);
//        g.draw3DRect(0, 0, (windowWidth/2)-1, windowHeight-22, true);
//        g.draw3DRect(windowWidth/2, 0, (windowWidth/2)-1, windowHeight-2, true);
//        g.draw3DRect(0, windowHeight - minimapHeight - 20, minimapWidth, minimapHeight, true);
    }

    // Compute the split window location X
    private int getWindowX(Tank tank) {
        int windowX = tank.getTankCenterX() - FRAME_WIDTH / 4;
        // handle two edge cases when tank is close to left wall or right wall
        if (windowX < 0) {
            windowX = 0;
        } else if (windowX >= MAP_WIDTH - FRAME_WIDTH / 2) {
            windowX = MAP_WIDTH - FRAME_WIDTH / 2;
        }

        return windowX;
    }

    // Compute the split window location Y
    private int getWindowY(Tank tank) {
        int windowY = tank.getTankCenterY() - FRAME_HEIGHT / 2;
        // handle two edge cases when tank is close to the top wall or bottom wall
        if (windowY < 0) {
            windowY = 0;
        } else if (windowY >= MAP_HEIGHT - FRAME_HEIGHT) {
            windowY = MAP_HEIGHT - FRAME_HEIGHT;
        }

        return windowY;
    }

    // A simple AI(in fact not a real AI) that continues to track the human player and shoot
    private void runEnemyAI() {
        Tank tank1 = playerManager.getPlayer1();
        Tank tank2 = playerManager.getPlayer2();
        tank2.switchShootOn();
        tank2.switchUpOn();

        float angle = computeAngle(tank1, tank2);
        float angleToRotateWithDirection = angle - 180 - tank2.getAngle(); // High school maths here
        if (Math.abs(angleToRotateWithDirection) < 330 ) {
            if (angleToRotateWithDirection < 0) {
                tank2.switchRightOff();
                tank2.switchLeftOn();
            } else {
                tank2.switchLeftOff();
                tank2.switchRightOn();
            }
        } else {
            tank2.switchLeftOff();
            tank2.switchRightOff();
        }
    }

    // Compute and get angle between two tanks
    private float computeAngle(Tank tank1, Tank tank2) {
        float angle = (float) Math.toDegrees(Math.atan2(tank2.getTankCenterY() - tank1.getTankCenterY(),
                tank2.getTankCenterX() - tank1.getTankCenterX()));

        if(angle < 0){
            angle += 360;
        }
        return angle;
    }

    // Copy the background tile to cover the whole map
    private void drawBackground(Graphics2D g) {
        BufferedImage background = spriteLoader.loadSprite(ResourceField.BACKGROUND);
        int width = MAP_WIDTH / background.getWidth() + 1;
        int height = MAP_HEIGHT / background.getHeight() + 1;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.drawImage(background, background.getWidth() * i, background.getHeight() * j, this);
            }
        }
    }

    private void drawWall(Graphics2D g) {
        for (Wall w: wallList) {
            w.draw(g);
        }
    }

    private void drawTanks(Graphics2D g) {
        playerManager.getPlayer1().draw(g);
        playerManager.getPlayer2().draw(g);
    }

    private List<Wall> createWallList() {
        List<Wall> walls = new ArrayList<>();
        for (int row = 0; row < LAYOUT.length; row++) {
            for (int col = 0; col < LAYOUT[0].length; col++) {
                if (LAYOUT[row][col] == ResourceField.WALL.getVal()) {
                    BufferedImage wall = spriteLoader.loadSprite(ResourceField.WALL);
                    walls.add(new Wall(col * WALL_SIZE, row * WALL_SIZE, wall));
                }
            }
        }
        return walls;
    }
}
