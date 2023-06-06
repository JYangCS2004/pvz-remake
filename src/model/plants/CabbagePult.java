package model.plants;

import model.Plant;
import model.RandSpawnManager;
import model.projectiles.PultProjectile;
import ui.GamePanel;

import java.awt.*;

public class CabbagePult extends Plant {
    private static int COST = 100;
    private static String TAG = "cp";
    private static int HEALTH = 30;

    private RandSpawnManager spawnManager;

    public CabbagePult(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        spawnManager = new RandSpawnManager(10, g, RandSpawnManager.Type.BULLET);
    }

    @Override
    public void update() {
        PultProjectile p = new PultProjectile(x, y, g);

        if (p.getTarget() != null) {
            spawnManager.spawn(p);
        }

        spawnManager.updateEach();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        spawnManager.drawEach(g);
    }
}
