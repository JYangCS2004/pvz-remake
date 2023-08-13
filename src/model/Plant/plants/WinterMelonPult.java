package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.PultManager;
import model.projectiles.Pult.PultProjectiles.Melon;
import model.projectiles.Pult.PultProjectiles.WinterMelon;
import ui.GamePanel;

import java.util.List;

public class WinterMelonPult extends Plant {
    private static int COST = 200;
    private static String TAG = "wmp";
    private static int HEALTH = 10;

    private PultManager spawnManager;

    private static int SPAWN_TIME = 120;
    private int counter = 0;

    public WinterMelonPult(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        spawnManager = g.getLobberManager();
    }

    @Override
    public void update() {
        super.update();
        Melon p = new WinterMelon(x, y+20, this, g, y / g.getTileSize());

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

    @Override
    public String getPlantCondition() {
        return "mp";
    }
}
