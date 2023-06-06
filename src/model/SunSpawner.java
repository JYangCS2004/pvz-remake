package model;

import ui.GamePanel;

import java.util.List;
import java.util.Random;

public class SunSpawner extends RandSpawnManager {
    private int totalSunCount;

    public SunSpawner(int spawnTime, GamePanel g) {
        super(spawnTime, g, Type.SUN);
    }

    @Override
    public void updateEach() {
        Random rand = new Random();
        spawn(new Sun(48 * rand.nextInt(gamePanel.getScreenRowSize()), 0, 1));
        super.updateEach();
    }

    public List<Entity> getSuns() {
        return allEntities;
    }

    public void incrementSun() {
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
