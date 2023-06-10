package model.SpawnManager.ProjectileManager;

import model.Entity;
import model.projectiles.Projectile;
import model.SpawnManager.SpawnManager;
import ui.GamePanel;

import java.util.Iterator;

public abstract class ProjectileManager extends SpawnManager {
    public ProjectileManager(GamePanel g) {
        super(g);
    }

    public void checkEach(){
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Projectile p = (Projectile) it.next();
            if (p.expired() || checkCollision(p)) {
                it.remove();
            }
        }
    }

    public void updateEach() {
        checkEach();
        super.updateEach();
    }

    public abstract boolean checkCollision(Projectile p);
}
