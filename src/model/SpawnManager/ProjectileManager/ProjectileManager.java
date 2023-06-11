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

    private List<Entity> removeList = new ArrayList<>();

    public void checkEach(){
        for (Entity entity : entities) {
            Projectile p = (Projectile) entity;
            if (p.expired() || checkCollision(p)) {
                // it.remove();
                removeList.add(p);
            }
        }
    }

    public void updateEach() {
        checkEach();

        for (Entity e : removeList) {
            entities.remove(e);
        }

        super.updateEach();
    }

    public abstract boolean checkCollision(Projectile p);

    public void remove(Entity e) {
        removeList.add(e);
    }
}
