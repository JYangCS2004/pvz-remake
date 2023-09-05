package model.SpawnManager.RandSpawnManager.RandSpawners;

import model.SpawnManager.RandSpawnManager.RandSpawnManager;
import model.Zombie.Zombie;
import model.Zombie.zombies.*;
import ui.GamePanel;

import java.util.Random;

public class ZombieManager extends RandSpawnManager {

    // private ArrayList<Zombie> removeQueue = new ArrayList<>();

    // temporary control over spawner
    private int keyBind;
    private int random;
    public ZombieManager(int spawnTime, GamePanel g) {
        super(spawnTime, g);
        /*
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_1) {
                    keyBind = 1;
                } else if (keyCode == KeyEvent.VK_3) {
                    keyBind = 3;
                } else {
                    keyBind = 0;
                }
            }
        }); */
    }

    @Override
    public void updateEach() {
        Random rand = new Random();
        int random = new Random().nextInt(5);
        //System.out.println(random);

        Zombie spawn;
        int y = 48 + 48 * rand.nextInt(gamePanel.getScreenRowSize() - 1);

        switch (random) {
            case 0:
                spawn = new DefaultZombie(gamePanel.getScreenWidth(), y, gamePanel);
                break;
            case 1:
                spawn = new Gargantuar(gamePanel.getScreenWidth(), y, gamePanel);
                break;
            case 2:
                spawn = new Zomboni(gamePanel.getScreenWidth(), y, gamePanel);
                break;
            case 3:
                spawn = new ScreenDoorZombie(gamePanel.getScreenWidth(), y, gamePanel);
                break;
            default:
                spawn = new PogoZombie(gamePanel.getScreenWidth(), y, gamePanel);
                 break;
        }

        spawn(spawn);

        super.updateEach();
    }

    public void removeZombie(Zombie e){
        entities.remove(e);
    }
}
