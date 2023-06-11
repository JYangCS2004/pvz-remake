package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.Bullet.BulletProjectiles.IcePea;
import ui.GamePanel;

import java.util.List;

public class IcePeaShooter extends Plant {

    private final static int SPAWN_TIME = 60;
    private int counter = SPAWN_TIME;
    private final ProjectileManager projectileManager;
    private static final int HEALTH = 20;
    // private static final int COST = 150;
    private static final int COST = 1;
    private static final String TAG = "ips";
    public IcePeaShooter(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getShooterManager();
        row = y / g.getTileSize();
    }

    @Override
    public void update() {

        if (canShoot()) {
            counter--;
            if (counter == 0) {
                counter = SPAWN_TIME;
                projectileManager.spawn(new IcePea(x+20, y +15, this, g));
            }
        } else {
            counter = SPAWN_TIME;
        }
    }

    private boolean canShoot() {
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(row);
        testable.removeIf(e -> e.getX() < x);
        return testable.size() != 0;
    }
}
