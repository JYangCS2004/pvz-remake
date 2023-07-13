package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.Zombie.Zombie;
import model.projectiles.Bullet.BulletProjectiles.ChomperProjectile;
import ui.GamePanel;

import java.util.List;

public class HypnoShroom extends Plant {
    private final ProjectileManager projectileManager;
    private static final int HEALTH = 2;
    private static final int COST = 75;
    private static final String TAG = "hs";
    private int shortestDist = 99999;
    public HypnoShroom(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getShooterManager();
        row = y / g.getTileSize();
    }

    @Override
    public void update() {
        super.update();
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());
        Zombie z = checkInRange(testable);
        if(health == 1 && z != null){
            System.out.println("ran");
            projectileManager.spawn(new ChomperProjectile(x, y, this, g));
            g.getPlantManager().spawnWithoutRegister(
                    new PlantZombie(z.getX(), z.getY(), z.getHealth(), -z.getDefaultSpeed(), z.getDamage(), z.getEatTime(), g));
            g.getPlantManager().remove(this);
        }
    }

    private Zombie checkInRange(List<Entity> test) {
        for (Entity e : test) {
            if (e.getX() <= x + g.getTileSize() * 1.5) {
                return (Zombie) e;
            }
        }
        return null;
    }
}
