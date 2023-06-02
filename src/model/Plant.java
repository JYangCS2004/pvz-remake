package model;

import ui.GamePanel;

import java.awt.*;

public class Plant extends Entity {
    private final static int SPAWN_TIME = 70;
    private RandSpawnManager projectileManager;

    private int health = 20;

    public Plant(int x, int y, GamePanel g) {
        super(x, y);
        super.g = g;
        speed = 0;
        projectileManager = new RandSpawnManager(SPAWN_TIME, g, RandSpawnManager.Type.BULLET);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 48, 48);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        projectileManager.drawEach(g);
    }

    @Override
    public void update() {
        projectileManager.spawn(new Projectile(x, y + 24, g));
        projectileManager.updateEach();
    }

    public void decreaseHealth() {
        this.health--;
    }

    public int getHealth() {
        return health;
    }
}
