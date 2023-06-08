package model.SpawnManager.RandSpawnManager;

import model.Entity;
import model.SpawnManager.SpawnManager;
import ui.GamePanel;

public abstract class RandSpawnManager extends SpawnManager {
    private final int spawnTime;
    private int counter;

    public RandSpawnManager(int spawnTime, GamePanel g) {
        super(g);
        this.spawnTime = spawnTime;
        counter = spawnTime;
    }

    @Override
    public void spawn(Entity e) {
        counter--;
        if (counter == 0) {
            super.spawn(e);
            counter = spawnTime;
        }
    }

    public void forceSpawn(Entity e){
        super.spawn(e);
    }

}
