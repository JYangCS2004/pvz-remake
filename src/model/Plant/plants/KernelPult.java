package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.PultManager;
import model.projectiles.Pult.PultProjectiles.ButterBlock;
import model.projectiles.Pult.PultProjectiles.CornProjectile;
import ui.GamePanel;

import java.util.List;
import java.util.Random;

public class KernelPult extends Plant {
    private static final int HEALTH = 10;
    private static final String TAG = "kp";
    private static final int COST = 100;

    private int counter = 0;
    private static final int SPAWN_TIME = 140;

    private PultManager spawnManager;

    public KernelPult(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        spawnManager = g.getLobberManager();
    }

    @Override
    public void update() {
        ButterBlock p = new ButterBlock(x, y+20, this, g, y / g.getTileSize());

        counter--;
        if (canShoot()) {
            if (counter <= 0) {
                Random rand = new Random();
                if (rand.nextInt(3) == 1) {
                    spawnManager.spawn(p);
                } else {
                    spawnManager.spawn(new CornProjectile(x, y + 20, this, g, y / g.getTileSize()));
                }

                counter = SPAWN_TIME;
            }
        }
    }

    private boolean canShoot() {
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(row);
        testable.removeIf(e -> e.getX() < x);
        return testable.size() != 0;
    }
}
