package model.spawnManagers;

import model.Entity;
import model.Projectile;
import model.SpawnManager;
import model.Zombie;
import ui.GamePanel;

import java.util.Iterator;
import java.util.List;

public abstract class ProjectileManager extends SpawnManager {
    public ProjectileManager(GamePanel g) {
        super(g);
    }

    public void checkEach(){
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Projectile p = (Projectile) it.next();
            if (checkCollision(p) || false) {
                it.remove();
            }
        }
    }

    public void updateEach() {
        checkEach();
        super.updateEach();
    }

    private boolean checkCollision(Projectile p) {

        List<Entity> possibleCollisions = gamePanel.getZombieSpawner().getEntitiesByRow(p.getRow());

        if (possibleCollisions.isEmpty()) {
            return false;
        }

        for (Entity possibleCollision : possibleCollisions) {
            Zombie e = (Zombie) possibleCollision;

            if (e.getBounds().intersects(p.getBounds())) {
                e.decreaseHealth(p.getDamage());

                if (e.getHealth() <= 0) {
                    entities.remove(e);
                }

                return true;
            }
        }
        return false;
    }
}
