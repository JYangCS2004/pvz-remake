package model.Plant.plants;

import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.Bullet.BulletProjectiles.CattailSpike;
import ui.GamePanel;

public class Cattail extends Plant {
    private static final int HEALTH = 40;
    private static final String TAG = "ct";
    private static final int COST = 225;
    private ProjectileManager projectileManager;
    private int counter = 0;

    public Cattail(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        projectileManager = g.getShooterManager();
    }

    @Override
    public void update() {
        super.update();
        if (counter <= 0) {
            if (g.getZombieSpawner().getEntities().size() != 0) {
                projectileManager.spawn(new CattailSpike(x, y, this, g));
                counter = 30;
            }
        } else {
            counter--;
        }
    }
}
