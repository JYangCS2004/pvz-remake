package model.projectiles;

import model.Entity;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CattailSpike extends Projectile {
    private static final int LIFETIME = 10000;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int DAMAGE = 2;

    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private int followDelay = 50;
    private Entity target;
    private double radius;
    private double angle = 150;

    public CattailSpike(int x, int y, Entity owner, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, 5, DAMAGE, LIFETIME, owner, new ArrayList<>(), g);
        this.x = x;
        this.y = y;

        double closest = 100000.0;
        List<Entity> zm = g.getZombieSpawner().getEntities();
        for (int i = 0; i < zm.size(); i++) {
            Entity e = zm.get(i);
            double temp = new Point((int)this.x, (int)this.y).distance(e.getX(), e.getY());

            if (temp < closest) {
                closest = temp;
                target = e;
            }
        }

        radius = (new Point((int)this.x, (int)this.y).distance(target.getX(), target.getY()));
    }


    @Override
    public void update() {

        double random = angle * Math.PI / 180.0;

        if (target != null) {

            if (super.y < target.getY()) {
                this.x += -Math.cos(random) * 2;
                this.y += -Math.sin(random) * 2;
            } else {
                this.x += -Math.cos(random) * 2;
                this.y += Math.sin(random) * 2;
            }


            double distance = (new Point((int)this.x, (int)this.y).distance(target.getX(), target.getY()));
            velocityX = (target.getX() - x) / (distance);
            velocityY = (target.getY() - y) / (distance);
        }

        x += 1;
        x += velocityX * 2;
        y += velocityY * 2;

        angle++;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval((int) x, (int) y, width, height);
        g.setColor(Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, width, height);
    }
}
