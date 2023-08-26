package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.physics.LocalPhysics;
import model.physics.Vector;
import model.projectiles.AOE.Beams.PotatoMineExplosion;
import ui.GamePanel;

import java.awt.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Squash extends Plant {
    private static int HEALTH = 30;
    private static String TAG = "sq";
    private static int COST = 50;
    private LocalPhysics physics;
    private boolean jumped;
    private int groundCount;

    public Squash(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        physics = new LocalPhysics(x, y, y);
        groundCount = 0;
    }

    @Override
    public void update() {
        super.update();
        if (!jumped) {
            initializeJump();
        }

        if (jumped && physics.isGrounded()) {
            groundCount++;
        }

        if (groundCount == 2) {
            g.getAOEManager().spawn(new PotatoMineExplosion((int) physics.getX(), (int) physics.getY(), this, g));
        }

        x = (int) physics.getX();
        y = (int) physics.getY();
        physics.update();
    }

    @Override
    public Rectangle getBounds() {
        if (jumped) {
            return new Rectangle((int) physics.getX(), (int) physics.getY(), width, height);
        } else {
            return super.getBounds();
        }
    }

    private void initializeJump() {
        Queue<Entity> entityQueue = new PriorityQueue<>(Comparator.comparingInt(Entity::getX));
        entityQueue.addAll(g.getZombieSpawner().getEntitiesByRow(row));

        for (Entity e : entityQueue) {
            int pos = (int) (e.getX() + 0.5 * g.getTileSize());
            if ((pos >= x - 0.5 * g.getTileSize() && pos <= x) ||
                    (pos <= x + 1.5 * g.getTileSize() && pos >= x)) {
                Vector force = new Vector(pos - x, -2000);
                force.normalize();
                force.mult(6);

                physics.applyForce(force);
                jumped = true;
                canBeEaten = false;
                break;
            }
        }
    }
}
