package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.projectiles.Bullet.Bullet;
import model.projectiles.Bullet.BulletProjectiles.ChomperProjectile;
import model.projectiles.Bullet.BulletProjectiles.FirePea;
import model.projectiles.Bullet.BulletProjectiles.IcePea;
import model.projectiles.Bullet.BulletProjectiles.Pea;
import ui.GamePanel;

import java.util.List;

public class TorchWood extends Plant {
    private static final int COST = 150;
    private static final int HEALTH = 10;
    private static final String TAG = "tw";

    public TorchWood(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
    }

    @Override
    public void update() {
        List<Entity> testable = g.getShooterManager().getEntities();

        for (int i = 0; i < testable.size(); i++) {
            Bullet b = (Bullet) testable.get(i);
            if (b instanceof ChomperProjectile) {
                continue;
            }

            if (b.getBounds().intersects(getBounds())) {
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
                    testable.add(newOne);
                }
            }
        }
    }
}
