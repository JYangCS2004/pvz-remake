package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BeamManager;
import model.projectiles.AOE.Beams.FumeFire;
import ui.GamePanel;

import java.util.List;

public class FumeShroom extends Plant {
    private final static String TAG = "fs";
    private final static int COST = 125;
    private final static int HEALTH = 20;

    private BeamManager beamManager;

    private int counter = 0;
    private final static int SPAWN_TIME = 75;


    public FumeShroom(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        beamManager = g.getAOEManager();
    }

    @Override
    public void update() {
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());

        if (checkInRange(testable)) {
            if (counter == 0) {
                beamManager.spawn(new FumeFire(x + g.getTileSize() / 2, y + 10, this, g));
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
