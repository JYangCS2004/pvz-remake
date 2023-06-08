package model.SpawnManager.ProjectileManager.ProjectileManagers;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.Zombie.Zombie;
import model.projectiles.AOE.Beam;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.util.List;

public class BeamManager extends ProjectileManager {
    public BeamManager(GamePanel g) {
        super(g);
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
                // currently assumes all projectiles are from plant
                ((Plant) p.getOwner()).setTimer();
                if (e.getHealth() <= 0) {
                    gamePanel.getZombieSpawner().removeZombie(e);
                }
            }
        }
        if(((Beam) p).getBoom()){
            gamePanel.getPlantManager().remove(p.getOwner());
        }
        return false;
    }
}
