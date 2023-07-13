package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.Bullet.BulletProjectiles.Star;
import ui.GamePanel;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Starfruit extends Plant {
    private static int HEALTH = 20;
    private static final String TAG = "stf";
    private static final int COST = 125;
    private int counter;
    private final int spawnTime = 65;
    private ProjectileManager manager;

    private List<Line2D.Double> rayCasts;

    public Starfruit(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        rayCasts = new ArrayList<>();

        // ray casts along each path of projectiles
        rayCasts.add(new Line2D.Double(x, y, x, 0));
        rayCasts.add(new Line2D.Double(x, y, x, g.getScreenHeight()));
        rayCasts.add(new Line2D.Double(x, y, 2 * y + x, 0));
        rayCasts.add(new Line2D.Double(x, y, 2 * g.getScreenHeight() - 2 * y + x, g.getScreenHeight()));
        rayCasts.add(new Line2D.Double(x, y, 0, y));

        manager = g.getShooterManager();
        counter = spawnTime;
    }

    @Override
    public void update() {
        super.update();
        if (checkInRange()) {
            if (counter == 0) {
                rayCasts.forEach(r -> manager.spawn(new Star(x, y, this, g, r)));
                counter = spawnTime;
            } else {
                counter--;
            }
        } else {
            counter = spawnTime;
        }
    }


    private boolean checkInRange() {
        List<Entity> testable = g.getZombieSpawner().getEntities();
        for (Line2D.Double ray : rayCasts) {
            for (int i = 0; i < testable.size(); i++) {
                Entity e = testable.get(i);
                if (ray.intersects(e.getBounds())) {
                    return true;
                }
            }
        }

        return false;
    }
}
