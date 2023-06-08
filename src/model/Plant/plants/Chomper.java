package model.Plant.plants;

import model.Plant.Plant;
import model.projectiles.Bullet.BulletProjectiles.ChomperProjectile;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BulletManager;
import ui.GamePanel;

import java.awt.*;

public class Chomper extends Plant {
    static final int EATING_TIME = 500;
    final static int SPAWN_TIME = 5;
    final static int HEALTH = 40;
    final static int COST = 150;
    final static String TAG = "ch";
    private int eat_counter = 0;
    private int spawn_counter = 0;

    private final BulletManager projectileManager;

    public Chomper(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getShooterManager();
    }

    @Override
    public void update() {
        if (eat_counter <= 0 && spawn_counter <= 0) {
            projectileManager.spawn(new ChomperProjectile(x, y + 15, this, g));
            spawn_counter = SPAWN_TIME;
        }
        else{
            spawn_counter--;
            eat_counter--;
        }

        /*
        if (counter == 0) {
            List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());
            if (testable != null) {
                Iterator<Entity> it = testable.iterator();

                while (it.hasNext()) {
                    Entity e = it.next();
                    if (e.getBounds().intersects(getBounds())) {
                        counter = eatingtime;
                        it.remove();
                        break;
                    }
                }
            }
        } else {
            counter--;
        }

         */
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (eat_counter >= 0) {
            g.setColor(Color.red);
            g.drawString(Integer.toString(eat_counter), x + this.g.getImageLibrary().getTextXFix(tag), y + 20);
            g.setColor(Color.white);
        }
    }

    @Override
    public void decreaseHealth() {
        super.health--;
    }

    @Override
    public void setTimer(){
        eat_counter = EATING_TIME;
    }
}
