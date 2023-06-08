package model.SpawnManager.ProjectileManager.ProjectileManagers;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.Zombie.Zombie;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.util.Iterator;
import java.util.List;

public class BulletManager extends ProjectileManager {
    public BulletManager(GamePanel g) {
        super(g);
    }

    public boolean isCollided(){
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Projectile p = (Projectile) it.next();
            if (checkCollision(p)) {
                System.out.println("called");
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision(Projectile p) {

        List<Entity> possibleCollisions = gamePanel.getZombieSpawner().getEntities();

        if (possibleCollisions.isEmpty()) {
            return false;
        }

        for (Entity possibleCollision : possibleCollisions) {
            Zombie e = (Zombie) possibleCollision;

            if (e.getBounds().intersects(p.getBounds())) {
                e.decreaseHealth(p.getDamage());
                ((Plant) p.getOwner()).setTimer();
                if (e.getHealth() <= 0) {
                    gamePanel.getZombieSpawner().removeZombie(e);
                }
                return true;
            }
        }
        return false;
    }
}
