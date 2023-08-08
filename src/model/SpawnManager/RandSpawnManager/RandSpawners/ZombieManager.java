package model.SpawnManager.RandSpawnManager.RandSpawners;

import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.Zombie.Zombie;
import model.Zombie.zombies.DefaultZombie;
import model.Zombie.zombies.PogoZombie;
import ui.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class ZombieManager extends RandSpawnManager {

    // private ArrayList<Zombie> removeQueue = new ArrayList<>();

    // temporary control over spawner
    private int keyBind;
    public ZombieManager(int spawnTime, GamePanel g) {
        super(spawnTime, g);
    }

    @Override
    public void updateEach() {
        Random rand = new Random();

        Zombie spawn;
        if (keyBind == 0) {
            spawn = new DefaultZombie(gamePanel.getScreenWidth(), 48 + 48 * rand.nextInt(gamePanel.getScreenRowSize()-1), gamePanel);
        } else {
            spawn = new PogoZombie(gamePanel.getScreenWidth(), 48 + 48 * rand.nextInt(gamePanel.getScreenRowSize()-1), gamePanel);
        }

        spawn(spawn);

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    keyBind = 1;
                } else {
                    keyBind = 0;
                }
            }
        });

        super.updateEach();
    }
    public void removeZombie(Zombie e){
        entities.remove(e);
    }
}
