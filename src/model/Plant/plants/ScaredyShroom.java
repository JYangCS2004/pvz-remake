package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.Bullet.BulletProjectiles.SporeBullet;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class ScaredyShroom extends Plant {

    private final static int SPAWN_TIME = 60;
    private int counter = SPAWN_TIME;
    private final ProjectileManager projectileManager;
    private static final int HEALTH = 10;
    private static final int COST = 25;
    private static final String TAG = "ss";
    private boolean scared = false;
    public ScaredyShroom(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getShooterManager();
        row = y / g.getTileSize();
    }

    @Override
    public void update() {
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());

        if (checkInRange(testable)) {
            if (counter == 0) {
                projectileManager.spawn(new SporeBullet(x + g.getTileSize() / 2, y + 10, 150, this, g));
                counter = SPAWN_TIME;
            } else {
                counter--;
            }
        } else {
            counter = SPAWN_TIME;
        }
    }

    @Override
    public void draw(Graphics g) {
        if(!scared){
            super.draw(g, tag);
        }
        else{
            super.draw(g, "sss");
        }
    }

    private boolean checkInRange(List<Entity> test) {
        if(test.isEmpty()){
            scared = false;
            return false;
        }
        for (Entity e : test) {
            if (e.getX() <= x + g.getTileSize() * 3) {
                scared = true;
                return false;
            }
        }
        return true;
    }
}
