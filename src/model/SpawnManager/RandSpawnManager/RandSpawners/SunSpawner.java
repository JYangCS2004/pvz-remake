package model.SpawnManager.RandSpawnManager.RandSpawners;

import model.Entity;
import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.Sun;
import ui.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SunSpawner extends RandSpawnManager {
    private int totalSunCount;
    public static final int DEFAULT = 50;
    private List<Point> mousePoints;

    public SunSpawner(int spawnTime, GamePanel g) {
        super(spawnTime, g);
        mousePoints = new ArrayList<>();
    }

    @Override
    public void updateEach() {
        Random rand = new Random();
        Sun s = new Sun(gamePanel.getTileSize() * rand.nextInt(gamePanel.getScreenRowSize()), 0, 1);

        for (int i = 0; i < mousePoints.size(); i++) {
            checkMouseHoveredPoints(mousePoints.get(i));
        }

        mousePoints.clear();

        spawn(s);

        super.updateEach();
    }



    public List<Entity> getSuns() {
        return entities;
    }

    public void incrementSun(int amount) {
        totalSunCount += amount;
        //for testing
        //totalSunCount += 500;
    }

    public int getSunCount(){
        return totalSunCount;
    }

    public void deductSum(int amount) {
        totalSunCount -= amount;
    }

    public void addMousePoint(Point mouse) {
        mousePoints.add(mouse);
    }

    private void checkMouseHoveredPoints(Point p) {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Sun s = (Sun) it.next();
            if (new Area(s.getBounds()).contains(p.x, p.y)) {
                incrementSun(SunSpawner.DEFAULT);
                it.remove();
                break;
            }
        }
    }

}
