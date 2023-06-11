package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.projectiles.Bullet.Bullet;
import model.projectiles.Bullet.BulletProjectiles.FirePea;
import model.projectiles.Bullet.BulletProjectiles.IcePea;
import model.projectiles.Bullet.BulletProjectiles.Pea;
import ui.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class TorchWood extends Plant {
    private static final int COST = 0;
    private static final int HEALTH = 50;
    private static final String TAG = "tw";

    public TorchWood(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
    }

    @Override
    public void update() {
        List<Entity> testable = g.getShooterManager().getEntities();
        List<Entity> newBullets = new ArrayList<>();

        for (Entity e : testable) {
            Bullet b = (Bullet) e;
            if (e.getBounds().intersects(getBounds())) {
                if (b.getHitting() == null || !b.getHitting().equals(this)) {
                    Bullet newOne;
                    if (b instanceof IcePea) {
                        newOne = new Pea(b.getX(), b.getY(), this, g);
                        newOne.setHitting(this);
                    } else {
                        newOne = new FirePea(b.getX(), b.getY(), this, g);
                        newOne.setHitting(this);
                    }

                    g.getShooterManager().remove(b);
                    newBullets.add(newOne);
                }
            }
        }

        testable.addAll(newBullets);

    }
}
