package model.SpawnManager.RandSpawnManager.RandSpawners;

import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.Zombie.Zombie;
import model.Zombie.zombies.DefaultZombie;
import ui.GamePanel;

import java.util.Random;

public class ZombieManager extends RandSpawnManager {

    // private ArrayList<Zombie> removeQueue = new ArrayList<>();
    public ZombieManager(int spawnTime, GamePanel g) {
        super(spawnTime, g);
    }
    @Override
    public void updateEach() {
        Random rand = new Random();
        spawn(new DefaultZombie(gamePanel.getScreenWidth(), 48 * rand.nextInt(gamePanel.getScreenRowSize()), gamePanel));

        super.updateEach();
    }
    public void removeZombie(Zombie e){
        entities.remove(e);
    }
}
