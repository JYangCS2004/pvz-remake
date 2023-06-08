package model.SpawnManager.RandSpawnManager.RandSpawners;

import model.Entity;
import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.Zombie.Zombie;
import model.Zombie.zombies.DefaultZombie;
import ui.GamePanel;

import java.util.Random;

public class ZombieManager extends RandSpawnManager {
    public ZombieManager(int spawnTime, GamePanel g) {
        super(spawnTime, g);
    }

    @Override
    public void updateEach() {
        Random rand = new Random();
        spawn(new DefaultZombie(gamePanel.getScreenWidth(), 48 * rand.nextInt(gamePanel.getScreenRowSize()), gamePanel));

        for (Entity e : entities) {
            Zombie z = (Zombie)e;
            e.update();
        }
    }
    public void removeZombie(Zombie e){
        entities.remove(e);
    }
}
