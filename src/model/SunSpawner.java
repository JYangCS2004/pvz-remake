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
        spawn(new Sun(48 * rand.nextInt(gamePanel.getScreenRowSize()), 0));
        super.updateEach();
    }

    public List<Entity> getSuns() {
        return rowEntities.get(0);
    }

    public void incrementSun() {
        totalSunCount += 50;
    }

    public int getSunCount(){
        return totalSunCount;
    }
}
