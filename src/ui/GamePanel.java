package ui;

import model.PlantManager;
import model.RandSpawnManager;
import model.SpawnManager;

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

    private SpawnManager plantManager = new PlantManager(this);
    private RandSpawnManager zombieSpawner = new RandSpawnManager(100, this, RandSpawnManager.Type.ENEMY);

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);
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

        getMouseTracker(g2);
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

    public RandSpawnManager getZombieSpawner() {
        return zombieSpawner;
    }
    public SpawnManager getPlantManager() {return plantManager; }
}
