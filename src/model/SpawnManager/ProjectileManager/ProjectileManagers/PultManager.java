package model.SpawnManager.ProjectileManager.ProjectileManagers;

import model.Entity;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.StatusEffect.Effects.PierceEffect;
import model.StatusEffect.StatusEffect;
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
                e.decreaseHealth(p);

                for (String s : p.getOnHitEffects()) {
                    StatusEffect eff = e.getEffectManager().select(e, s);
                    if (s.equals("PIERCING") && e.getEffectManager().contains("SHIELD")) {
                        ((PierceEffect) eff).setDamage(p.getDamage());
                    }

                    e.getEffectManager().add(eff);
                }

                if (e.getHealth() <= 0) {
                    gamePanel.getZombieSpawner().removeZombie(e);
                }

                return true;
            }
        }
        return false;
    }
}
