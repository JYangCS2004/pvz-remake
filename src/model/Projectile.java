package model;

import ui.GamePanel;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public abstract class Projectile extends Entity {

    protected int damage;
    protected int lifetime;

    public Projectile(int x, int y, int width, int height, int speed, int damage, int lifetime, GamePanel g) {
        super(x, y);
        super.g = g;
        super.width = width;
        super.height = height;
        super.speed = speed;
        this.damage = damage;
        this.lifetime = lifetime;
    }

    public boolean checkCollision() {

        List<Entity> possibleCollisions = g.getZombieSpawner().getEntitiesByRow((y - 15) / g.getTileSize());

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
                e.decreaseHealth(damage);

                if (e.getHealth() <= 0) {
                    it.remove();
                }

                return true;
            }
        }

        return false;
    }

    public boolean expired(){
        return (lifetime <= 0) || checkCollision();
    }


    public void update() {
        super.update();
        lifetime--;
        checkCollision();
    }
}
