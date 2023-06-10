package model.SpawnManager.RandSpawnManager.RandSpawners;

import model.Entity;
import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.Zombie.Zombie;
import model.Zombie.zombies.DefaultZombie;
import ui.GamePanel;

import java.util.ArrayList;
import java.util.Random;

public class ZombieManager extends RandSpawnManager {

    private ArrayList<Zombie> removeQueue = new ArrayList<>();
    public ZombieManager(int spawnTime, GamePanel g) {
        super(spawnTime, g);
    }
    @Override
    public void updateEach() {
        Random rand = new Random();
        spawn(new DefaultZombie(gamePanel.getScreenWidth(), 48 * rand.nextInt(gamePanel.getScreenRowSize()), gamePanel));

        for(Zombie e: removeQueue){
            entities.remove(e);
        }
        removeQueue = new ArrayList<>();

        for (Entity e : entities) {
            e.update();
        }
    }
    public void removeZombie(Zombie e){
        removeQueue.add(e);
    }
}
