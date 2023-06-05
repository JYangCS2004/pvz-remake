package model;

import ui.GamePanel;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RandSpawnManager extends SpawnManager {
    public enum Type {BULLET, ENEMY, SUN}
    private final int spawnTime;
    private int counter;
    private Type type;

    public RandSpawnManager(int spawnTime, GamePanel g) {
        super(g);
        this.spawnTime = spawnTime;
    }

    public RandSpawnManager(int spawnTime, GamePanel g, Type t) {
        super(g);
        this.spawnTime = spawnTime;
        counter = spawnTime;
        this.type = t;
    }

    @Override
    public void spawn(Entity e) {
        counter--;
        if (counter == 0) {
            super.spawn(e);
            counter = spawnTime;
        }
    }

    @Override
    public void updateEach() {
        if (type == Type.ENEMY) {
            Random rand = new Random();
            spawn(new Zombie(gamePanel.getScreenWidth(), 48 * rand.nextInt(gamePanel.getScreenRowSize()), gamePanel));

        } else if (type == Type.BULLET) {

            for (int i : rowEntities.keySet()) {
                Iterator<Entity> it = rowEntities.get(i).iterator();

                while (it.hasNext()) {
                    Projectile p = (Projectile) it.next();
                    if (p.expired() || checkCollision(p)) {
                        it.remove();
                    }
                }
            }
        }

        super.updateEach();
    }

    public boolean isCollided(){
        for (int i : rowEntities.keySet()) {
            Iterator<Entity> it = rowEntities.get(i).iterator();

            while (it.hasNext()) {
                Projectile p = (Projectile) it.next();
                if (checkCollision(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCollision(Projectile p) {

        List<Entity> possibleCollisions = gamePanel.getZombieSpawner().getEntitiesByRow((p.y - 15) / gamePanel.getTileSize());

        if (possibleCollisions == null || possibleCollisions.isEmpty()) {
            return false;
        }

        Iterator<Entity> it = possibleCollisions.iterator();
        while (it.hasNext()) {
            Zombie e = (Zombie) it.next();
            Rectangle pBounds = p.getBounds();
            Rectangle zBounds = e.getBounds();

            if (zBounds.intersects(pBounds)) {
                e.decreaseHealth(p.damage);

                if (e.getHealth() <= 0) {
                    it.remove();
                }

                return true;
            }
        }
        return false;
    }

}
