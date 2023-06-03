package model;

import ui.GamePanel;

import java.awt.*;
import java.util.Iterator;
import java.util.Queue;

public class Projectile extends Entity {
    private final int size = 16;

    public Projectile(int x, int y, GamePanel g) {
        super(x, y);
        super.g = g;
        speed = 5;
        width = size;
        height = size;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
        g.setColor(Color.WHITE);
    }

    public boolean checkCollision() {
        Queue<Entity> possibleCollisions = g.getZombieSpawner().getEntitiesByRow((y - 15) / g.getTileSize());

        if (possibleCollisions == null || possibleCollisions.isEmpty()) {
            return false;
        }

        /*
        Zombie e = (Zombie) possibleCollisions.get(0);
        Rectangle pBounds = this.getBounds();
        Rectangle zBounds = e.getBounds();

        if (pBounds.intersects(zBounds)) {
            e.decreaseHealth();

            if (e.getHealth() <= 0) {
                possibleCollisions.remove(e);
            }

            return true;
        } */


        Iterator<Entity> it = possibleCollisions.iterator();
        while (it.hasNext()) {
            Zombie e = (Zombie) it.next();
            Rectangle pBounds = this.getBounds();
            Rectangle zBounds = e.getBounds();

            if (zBounds.intersects(pBounds)) {
                e.decreaseHealth();

                if (e.getHealth() <= 0) {
                    it.remove();
                }

                return true;
            }
        }

        return false;
    }

    public void update() {
        super.update();
        checkCollision();
    }
}
