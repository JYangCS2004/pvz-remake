package model.SpawnManager.ProjectileManager;

import model.Entity;
import model.SpawnManager.SpawnManager;
import model.projectiles.Projectile;
import ui.GamePanel;

public abstract class ProjectileManager extends SpawnManager {
    public ProjectileManager(GamePanel g) {
        super(g);
    }
    private void checkEach(){
        for (int i = 0; i < entities.size(); i++) {
            Projectile p = (Projectile) entities.get(i);
            if (p.expired() || checkCollision(p)) {
            //if (p.expired()) {
                // it.remove();
                entities.remove(p);
            }
        }
    }

    public void updateEach() {
        checkEach();
        super.updateEach();
    }

    protected void update() {
        super.updateEach();
    }

    public abstract boolean checkCollision(Projectile p);

    public void remove(Entity e) {
        entities.remove(e);
    }
}
