package model;

import ui.GamePanel;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class Projectile extends Entity {
    private final int size = 16;

    public Projectile(int x, int y, GamePanel g) {
        super(x, y);
        super.g = g;
        speed = 5;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
        g.setColor(Color.WHITE);
    }

    public boolean checkCollision() {
        List<Entity> possibleCollisions = g.getZombieSpawner().getEntitiesByRow((y - 24) / g.getTileSize());

        if (possibleCollisions == null) {
            return false;
        }

        Iterator<Entity> it = possibleCollisions.iterator();
        while (it.hasNext()) {
            Zombie e = (Zombie) it.next();
            if (x >= e.getX() && x + size <= e.getX() + g.getTileSize() &&        // check x-bounds
                y >= e.getY() && y + size <= e.getY() + g.getTileSize()) {        // check y-bounds
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
