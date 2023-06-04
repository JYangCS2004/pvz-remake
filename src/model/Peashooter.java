package model;

import ui.GamePanel;

import java.awt.*;

public class Peashooter extends Plant{

    private final static int SPAWN_TIME = 70;
    private final RandSpawnManager projectileManager;
    static final int health = 20;
    static final String tag = "ps";
    public Peashooter(int x, int y, GamePanel g){
        super(x,y, health, tag, g);
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
        projectileManager.spawn(new Projectile(x, y + 15, g));
        projectileManager.updateEach();
    }
}