package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.Bullet.BulletProjectiles.SporeBullet;
import ui.GamePanel;

import java.util.List;

public class PuffShroom extends Plant {

    private final static int SPAWN_TIME = 60;
    private int counter = SPAWN_TIME;
    private final ProjectileManager projectileManager;
    private static final int HEALTH = 10;
    private static final int COST = 0;
    private static final String TAG = "psh";
    public PuffShroom(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getShooterManager();
        row = y / g.getTileSize();
    }

    @Override
    public void update() {
        super.update();
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());

        if (checkInRange(testable)) {
            if (counter == 0) {
                projectileManager.spawn(new SporeBullet(x + g.getTileSize() / 2, y + 10, 30, this, g));
                counter = SPAWN_TIME;
            } else {
                counter--;
            }
        } else {
            counter = SPAWN_TIME;
        }
    }


    private boolean checkInRange(List<Entity> test) {
        for (Entity e : test) {
            if (e.getX() <= x + g.getTileSize() * 4.5) {
                return true;
            }
        }
        return false;
    }
}
