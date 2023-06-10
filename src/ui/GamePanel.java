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
    private final int FPS = 60;
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int screenColSize = 16;
    final int screenRowSize = 12;
    final int screenHeight = screenRowSize * tileSize;
    final int screenWidth = screenColSize * tileSize;

    private Thread gameThread;

    private final PlantManager plantManager = new PlantManager(this);
    private final ZombieManager zombieSpawner = new ZombieManager(150,this);
    private final SunSpawner sunSpawner = new SunSpawner(100, this);
    private final BulletManager shooterManager = new BulletManager(this);
    private final PultManager lobberManager = new PultManager(this);
    private final BeamManager AOEManager = new BeamManager(this);
    private final PlantInterface plantInterface = new PlantInterface(this);
    final ImageLibrary imageLibrary = new ImageLibrary();

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        //lime green
        setBackground(new Color(50,205,50));
        setDoubleBuffered(true);

        addMouseListener(new MouseHandler(plantManager, sunSpawner, plantInterface));
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
        zombieSpawner.updateEach();
        plantManager.updateEach();

        shooterManager.updateEach();
        lobberManager.updateEach();
        AOEManager.updateEach();

        sunSpawner.updateEach();
    }

    public void getMouseTracker(Graphics g) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, this);
        int closestEdgeX = p.x / tileSize;
        int closestEdgeY = p.y / tileSize;

        g.drawRoundRect(closestEdgeX * tileSize,
                closestEdgeY * tileSize, tileSize, tileSize, 10, 10);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));

        zombieSpawner.drawEach(g2);
        plantManager.drawEach(g2);
        plantInterface.draw(g2, sunSpawner);

        shooterManager.drawEach(g2);
        lobberManager.drawEach(g2);
        AOEManager.drawEach(g2);

        getMouseTracker(g2);
        sunSpawner.drawEach(g2);
        g2.dispose();
    }

    public int getScreenRowSize() {
        return screenRowSize;
    }
    public int getScreenColSize() {return screenColSize; }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getTileSize() {
        return tileSize;
    }

    public ZombieManager getZombieSpawner() {
        return zombieSpawner;
    }

    public RandSpawnManager getSunSpawner(){return sunSpawner;}

    public PlantManager getPlantManager() {return plantManager; }

    public PultManager getLobberManager() {
        return lobberManager;
    }

    public BeamManager getAOEManager(){return AOEManager;}

    public BulletManager getShooterManager() {
        return shooterManager;
    }

    public ImageLibrary getImageLibrary(){return imageLibrary;}
}
