package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.Bullet.BulletProjectiles.Pea;
import ui.GamePanel;

import java.util.List;

public class GatlingPea extends Plant {

    private final static int SPAWN_TIME = 60;
    private int counter = SPAWN_TIME;
    private int burstFire = 4;
    private final int BURST_TIME = 5;
    private final ProjectileManager projectileManager;
    private static final int HEALTH = 10;
    private static final int COST = 400;
    private static final String TAG = "gp";
    public GatlingPea(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getShooterManager();
        row = y / g.getTileSize();
    }

    @Override
    public void update() {

        if (canShoot()) {
            counter--;
            if (counter == 0 && burstFire >= 1) {
                counter = BURST_TIME;
                burstFire--;
                projectileManager.spawn(new Pea(x+20, y +15, this, g));
            }
            else if(burstFire == 0){
                counter = SPAWN_TIME;
                burstFire = 4;
            }
        } else {
            counter = SPAWN_TIME;
            burstFire = 4;
        }
    }

    private boolean canShoot() {
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(row);
        testable.removeIf(e -> e.getX() < x);
        return testable.size() != 0;
    }

    @Override
    public String getPlantCondition(){
        return "rp";
    }

}
