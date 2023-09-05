package model.SpawnManager.ProjectileManager.ProjectileManagers;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.StatusEffect.Effects.PierceEffect;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;
import model.projectiles.AOE.Beam;
import model.projectiles.AOE.Beams.PepperFire;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.util.List;

public class BeamManager extends ProjectileManager {
    public BeamManager(GamePanel g) {
        super(g);
    }


    @Override
    public void updateEach() {
        for (int i = 0; i < entities.size(); i++) {
            Projectile p = (Projectile) entities.get(i);
            checkCollision(p);
            if (p.expired()) {
                entities.remove(p);
            }
        }

        update();
    }

    public boolean checkCollision(Projectile p) {
        if(((Beam) p).getBoom()){
            Entity owner = p.getOwner();
            gamePanel.getPlantManager().remove(owner);
        }

        List<Entity> possibleCollisions = gamePanel.getZombieSpawner().getEntities();
        boolean isCollided = false;

        if (possibleCollisions.isEmpty()) {
            return false;
        }

        for (int i = 0; i < possibleCollisions.size(); i++) {
            Zombie e = (Zombie) possibleCollisions.get(i);

            if (e.getRow() != p.getRow()) {
                if (p instanceof PepperFire) {
                    continue;
                }
            }

            if (e.getBounds().intersects(p.getBounds())) {
                isCollided = true;
                e.decreaseHealth(p);
                for(String s: p.getOnHitEffects()){
                    StatusEffect eff = e.getEffectManager().select(e, s);
                    if (s.equals("PIERCING") && e.getEffectManager().contains("SHIELD")) {
                        ((PierceEffect) eff).setDamage(p.getDamage());
                    }

                    e.getEffectManager().add(eff);
                }
                // currently assumes all projectiles are from plant
                ((Plant) p.getOwner()).setTimer();
                if (e.getHealth() <= 0) {
                    gamePanel.getZombieSpawner().removeZombie(e);
                }
            }
        }

        return isCollided;
    }
}
