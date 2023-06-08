package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.projectiles.Pult.PultProjectiles.PultProjectile;
import model.SpawnManager.ProjectileManager.ProjectileManagers.PultManager;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class CabbagePult extends Plant {
    private static int COST = 100;
    private static String TAG = "cp";
    private static int HEALTH = 30;

    private PultManager spawnManager;

    private static int SPAWN_TIME = 30;
    private int counter = SPAWN_TIME;

    public CabbagePult(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        spawnManager = g.getLobberManager();
    }

    @Override
    public void update() {
        PultProjectile p = new PultProjectile(x, y+20, this, g, y / g.getTileSize());
        List<Entity> test = g.getZombieSpawner().getEntitiesByRow(row);

        counter--;

        if (!test.isEmpty()) {
            if (counter == 0) {
                spawnManager.spawn(p);
                counter = SPAWN_TIME;
            }
        } else {
            counter = SPAWN_TIME;
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
