package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.PultManager;
import model.projectiles.Pult.PultProjectiles.CabbageProjectile;
import ui.GamePanel;

import java.util.List;

public class CabbagePult extends Plant {
    private static int COST = 100;
    private static String TAG = "cp";
    private static int HEALTH = 10;
    private PultManager spawnManager;
    private static int SPAWN_TIME = 120;
    private int counter = 0;

    public CabbagePult(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        spawnManager = g.getLobberManager();
    }

    @Override
    public void update() {
        super.update();
        CabbageProjectile p = new CabbageProjectile(x, y+20, this, g, y / g.getTileSize());

        counter--;
        if (canShoot()) {
            if (counter <= 0) {
                spawnManager.spawn(p);
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
