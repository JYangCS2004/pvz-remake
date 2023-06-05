package model.plants;

import model.Plant;
import model.RandSpawnManager;
import model.projectiles.PeashooterProjectile;
import ui.GamePanel;

import java.awt.*;

public class Peashooter extends Plant {

    private final static int SPAWN_TIME = 70;
    private final RandSpawnManager projectileManager;
    private static final int HEALTH = 20;
    private static final int COST = 100;
    private static final String TAG = "ps";
    public Peashooter(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
        this.projectileManager = new RandSpawnManager(SPAWN_TIME, g, RandSpawnManager.Type.BULLET);
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.GREEN);
        //g.fillRoundRect(x, y, 48, 48, 35, 35);
        super.draw(g);
        projectileManager.drawEach(g);
    }

    @Override
    public void update() {
        projectileManager.spawn(new PeashooterProjectile(x, y + 15, g));
        projectileManager.updateEach();
    }
}