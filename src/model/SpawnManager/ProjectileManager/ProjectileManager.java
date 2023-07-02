package model.SpawnManager.ProjectileManager;

import model.Entity;
import model.SpawnManager.SpawnManager;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.util.ArrayList;
import java.util.List;

public abstract class ProjectileManager extends SpawnManager {
    public ProjectileManager(GamePanel g) {
        super(g);
    }
    public void checkEach(){
        List<Entity> removeList = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {
            Projectile p = (Projectile) entities.get(i);
            if (p.expired() || checkCollision(p)) {
                // it.remove();
                entities.remove(p);
            }
        }

        // entities.removeAll(removeList);
    }

    public void updateEach() {
        checkEach();

        super.updateEach();
    }

    public abstract boolean checkCollision(Projectile p);

    public void remove(Entity e) {
        entities.remove(e);
    }
}
