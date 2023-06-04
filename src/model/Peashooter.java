package model;

import ui.GamePanel;

import java.awt.*;

public class Peashooter extends Plant{

    private final static int SPAWN_TIME = 70;
    private RandSpawnManager projectileManager;
    private int health = 20;
    public Peashooter(int x, int y, GamePanel g){
        super(x,y, g);
        this.projectileManager = new RandSpawnManager(70, g, RandSpawnManager.Type.BULLET);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRoundRect(x, y, 48, 48, 35, 35);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        projectileManager.drawEach(g);
    }
    @Override
    public void update() {
        projectileManager.spawn(new Projectile(x, y + 15, g));
        projectileManager.updateEach();
    }
    @Override
    public void decreaseHealth(){
        this.health--;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}
