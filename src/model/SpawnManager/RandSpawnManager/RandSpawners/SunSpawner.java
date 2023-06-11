package model.SpawnManager.RandSpawnManager.RandSpawners;

import model.Entity;
import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.Sun;
import ui.GamePanel;

import java.util.List;
import java.util.Random;

public class SunSpawner extends RandSpawnManager {
    private int totalSunCount;
    private int counter = 10;
    public static final int DEFAULT = 50;

    public SunSpawner(int spawnTime, GamePanel g) {
        super(spawnTime, g);
    }

    @Override
    public void updateEach() {
        Random rand = new Random();
        Sun s = new Sun(48 * rand.nextInt(gamePanel.getScreenRowSize()), 0, 1);
        spawn(s);

        super.updateEach();
    }



    public List<Entity> getSuns() {
        return entities;
    }

    public void incrementSun(int amount) {
        totalSunCount += 50;
        //for testing
        //totalSunCount += 500;
    }

    public int getSunCount(){
        return totalSunCount;
    }

    public void deductSum(int amount) {
        totalSunCount -= amount;
    }
}
