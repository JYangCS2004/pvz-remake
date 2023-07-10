package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.AOE.Beams.GloomShroomSpore;
import ui.GamePanel;

import java.awt.*;

public class GloomShroom extends Plant {

    private final static int SPAWN_TIME = 60;
    private int counter = SPAWN_TIME;
    private int burstFire = 4;
    private final int BURST_TIME = 5;
    private final ProjectileManager projectileManager;
    private static final int HEALTH = 30;
    private static final int COST = 150;
    private static final String TAG = "gs";
    public GloomShroom(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getAOEManager();
    }

    @Override
    public void update() {

        if (canShoot()) {
            counter--;
            if (counter == 0 && burstFire >= 1) {
                counter = BURST_TIME;
                burstFire--;
                projectileManager.spawn(new GloomShroomSpore(x, y, this, g));
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

    private boolean canShoot(){
        int t = g.getTileSize();
        for(Entity e: g.getZombieSpawner().getEntities()){
            if(e.getBounds().intersects(new Rectangle(x-t-1, y-t, 3*t+2, 3*t))){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getPlantCondition(){
        return "fs";
    }
}
