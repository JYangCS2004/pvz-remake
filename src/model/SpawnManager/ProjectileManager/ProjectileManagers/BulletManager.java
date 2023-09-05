package model.SpawnManager.ProjectileManager.ProjectileManagers;

import model.Entity;
import model.Plant.Plant;
import model.Plant.plants.Chomper;
import model.SpawnManager.ProjectileManager.ProjectileManager;
import model.Zombie.Zombie;
import model.Zombie.zombies.Gargantuar;
import model.projectiles.Bullet.BulletProjectiles.CattailSpike;
import model.projectiles.Bullet.BulletProjectiles.ChomperProjectile;
import model.projectiles.Bullet.BulletProjectiles.Star;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.util.List;

public class BulletManager extends ProjectileManager {
    public BulletManager(GamePanel g) {
        super(g);
    }

    private void applyDamage(Zombie e, Projectile p) {
        e.decreaseHealth(p);
        if (e.getHealth() <= 0) {
            gamePanel.getZombieSpawner().removeZombie(e);
        }
    }

    public boolean checkCollision(Projectile p) {

        List<Entity> possibleCollisions = gamePanel.getZombieSpawner().getEntities();

        if (possibleCollisions.isEmpty()) {
            return false;
        }

        for (int i = 0; i < possibleCollisions.size(); i++) {
            Zombie e = (Zombie) possibleCollisions.get(i);

            if (e.getBounds().intersects(p.getBounds())) {
                System.out.println("hit");
                if (!(p instanceof CattailSpike) && !(p instanceof Star)) {
                    if (e.getRow() != p.getRow()) {
                        continue;
                    }
                }

                //only added status effect on bullet manager so far
                for(String s: p.getOnHitEffects()) {
                    if ((e instanceof Gargantuar) && (p instanceof ChomperProjectile)) {
                        continue;
                    }
                    e.getEffectManager().add(e.getEffectManager().select(e, s));
                }

                // currently assumes all projectiles are from plant
                Plant pt = (Plant) p.getOwner();
                if (e instanceof Gargantuar) {
                    if (pt.getTag().equals("ch")) {
                        ((Chomper) pt).setAttackState();
                    }
                } else {
                    pt.setTimer();
                }

                if (p instanceof CattailSpike) {
                    if (e == ((CattailSpike) p).getTarget()) {
                        applyDamage(e, p);
                        return true;
                    }
                } else {
                    applyDamage(e, p);
                    return true;
                }
            }
        }
        return false;
    }
}
