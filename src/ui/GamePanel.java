package ui;

import model.*;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BeamManager;
import model.SpawnManager.SpawnManagers.PlantManager;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BulletManager;
import model.SpawnManager.ProjectileManager.ProjectileManagers.PultManager;
import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.SpawnManager.RandSpawnManager.RandSpawners.SunSpawner;
import model.SpawnManager.RandSpawnManager.RandSpawners.ZombieManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private int gameState;
    public int mouseState;
    public static final int SELECTION_STATE = 0;
    public static final int GAME_STATE = 1;
    private final int FPS = 60;
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale;
    private final int screenColSize = 16;
    private final int screenRowSize = 12;
    private final int screenHeight = screenRowSize * tileSize;
    private final int screenWidth = screenColSize * tileSize;

    private Thread gameThread;
    private SelectionScreen selectionScreen;
    private final PlantManager plantManager = new PlantManager(this);
    private final ZombieManager zombieSpawner = new ZombieManager(150,this);
    private final SunSpawner sunSpawner = new SunSpawner(100, this);
    private final BulletManager shooterManager = new BulletManager(this);
    private final PultManager lobberManager = new PultManager(this);
    private final BeamManager AOEManager = new BeamManager(this);
    private final PlantInterface plantInterface = new PlantInterface(this);
    final ImageLibrary imageLibrary = new ImageLibrary(this);

    public GamePanel() {
        gameState = SELECTION_STATE;
        setLayout(null);

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        //lime green
        setBackground(new Color(50,205,50));
        setDoubleBuffered(true);
        setFocusable(true);
        setVisible(true);

        selectionScreen = new SelectionScreen(this, imageLibrary, plantInterface);
        addMouseListener(new MouseHandler(plantManager, sunSpawner, plantInterface, selectionScreen));
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            validate();
            repaint();

            try {
                double remaining = nextDrawTime - System.nanoTime();
                remaining /= 1000000;

                if (remaining < 0) {
                    remaining = 0;
                }

                Thread.sleep((long) remaining);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameState == SELECTION_STATE) {
            // keep this in case idk :D
        } else {
            zombieSpawner.updateEach();
            plantManager.updateEach();

            shooterManager.updateEach();
            lobberManager.updateEach();
            AOEManager.updateEach();

            sunSpawner.updateEach();
        }
    }

    private void getMouseTracker(Graphics g) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, this);
        int closestEdgeX = p.x / tileSize;
        int closestEdgeY = p.y / tileSize;

        if (gameState == SELECTION_STATE) {
            if (closestEdgeX >= 1 && closestEdgeY >= 2 && closestEdgeY <= 5 && closestEdgeX <= 9) {
                g.drawRoundRect(closestEdgeX * tileSize,
                        closestEdgeY * tileSize, tileSize, tileSize, 10, 10);
            }
        } else {
            if (mouseState == 1) {
                g.setColor(Color.red);
            }
            g.drawRoundRect(closestEdgeX * tileSize,
                    closestEdgeY * tileSize, tileSize, tileSize, 10, 10);
            g.setColor(Color.white);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        g2.setStroke(new BasicStroke(2));

        if (gameState == SELECTION_STATE) {
            selectionScreen.draw(g2);
        } else {
            plantManager.drawEach(g2);
            zombieSpawner.drawEach(g2);

            shooterManager.drawEach(g2);
            lobberManager.drawEach(g2);
            AOEManager.drawEach(g2);
            sunSpawner.drawEach(g2);
        }


        plantInterface.draw(g2, sunSpawner);
        getMouseTracker(g2);

        if (gameState == GAME_STATE) {
            g2.dispose();
        }
    }

    public int getScreenRowSize() {
        return screenRowSize;
    }
    public int getScreenColSize() { return screenColSize; }

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight(){return screenHeight;}

    public int getTileSize() {
        return tileSize;
    }

    public ZombieManager getZombieSpawner() {
        return zombieSpawner;
    }

    public RandSpawnManager getSunSpawner(){
        return sunSpawner;
    }

    public PlantManager getPlantManager() {
        return plantManager;
    }

    public PultManager getLobberManager() {
        return lobberManager;
    }

    public BeamManager getAOEManager(){
        return AOEManager;
    }

    public BulletManager getShooterManager() {
        return shooterManager;
    }

    public ImageLibrary getImageLibrary(){return imageLibrary;}

    public PlantInterface getPlantInterface() {
        return plantInterface;
    }

    public int getGameState() {
        return gameState;
    }
    
    public void startGame() {
        gameState = GAME_STATE;
    }
}
