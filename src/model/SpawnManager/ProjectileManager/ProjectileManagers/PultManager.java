package model.SpawnManager.ProjectileManager.ProjectileManagers;

import model.Entity;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.projectiles.Projectile;
import model.Zombie.Zombie;
import ui.GamePanel;

import java.util.List;

public class PultManager extends ProjectileManager {
    public PultManager(GamePanel g) {
        super(g);
    }

    public boolean checkCollision(Projectile p) {

        List<Entity> possibleCollisions = gamePanel.getZombieSpawner().getEntitiesByRow(p.getRow());
        if (possibleCollisions.isEmpty()) {
            return false;
        }

        for (Entity possibleCollision : possibleCollisions) {
            Zombie e = (Zombie) possibleCollision;
            if (e.getBounds().intersects(p.getBounds())) {
                e.decreaseHealth(p.getDamage());

                if (e.getHealth() <= 0) {
                    gamePanel.getZombieSpawner().removeZombie(e);
                }

                return true;
            }
        }
        return false;
    }
}
