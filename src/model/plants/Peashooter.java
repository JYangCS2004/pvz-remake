package model.plants;

import model.Entity;
import model.Plant;
import model.projectiles.PeashooterProjectile;
import model.spawnManagers.ProjectileManager;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class Peashooter extends Plant {

    private final static int SPAWN_TIME = 70;
    private int counter = SPAWN_TIME;
    private final ProjectileManager projectileManager;
    private static final int HEALTH = 20;
    private static final int COST = 100;
    private static final String TAG = "ps";
    public Peashooter(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getShooterManager();
        row = y / g.getTileSize();
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.GREEN);
        //g.fillRoundRect(x, y, 48, 48, 35, 35);
        super.draw(g);
    }

    @Override
    public void update() {
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(row);

        if (!testable.isEmpty()) {
            counter--;
            if (counter == 0) {
                counter = SPAWN_TIME;
                projectileManager.spawn(new PeashooterProjectile(x+20, y +15, g, y / g.getTileSize()));
            }
        } else {
            counter = SPAWN_TIME;
        }
    }
}