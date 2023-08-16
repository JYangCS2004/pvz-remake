package model.Zombie.zombies;

import model.Entity;
import model.StatusEffect.Effects.JumpEffect;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;
import model.physics.LocalPhysics;
import model.physics.Vector;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class PogoZombie extends Zombie {
    static final double SPEED = -0.5;
    static final int DAMAGE = 1;
    static final int HEALTH = 100;
    static final int EAT_TIME = 30;
    static final int HEIGHT = 48;
    static final int WIDTH = 48;

    private double multiplier = 1;

    private boolean canJump;

    private LocalPhysics physics;
    private Vector movingForce = new Vector(-1, -3);
    private Vector jumpForce = new Vector(0, -4);
    private Vector normalJump = new Vector(-1, -4);
    private boolean escape;

    private double defaultLinear = -0.7;

    public PogoZombie(int x, int y, GamePanel g) {
        super(x, y, 0, 0, HEALTH, EAT_TIME, WIDTH, HEIGHT, g);
        movingForce.normalize();
        movingForce.mult(5);

        effectManager.add(new JumpEffect(this, 70));

        physics = new LocalPhysics(x, y, y);
    }

    public void update() {
        normalJump.x = multiplier * defaultLinear;

        if (!effectManager.contains("CHILL")) {
            physics.revertGravity();
            multiplier = 1;
        }

        if (isJumping()) {
            performJumpMechanic();
        }

        super.update();
    }


    private void performJumpMechanic() {
        escape = false;
        List<Entity> testable = g.getPlantManager().getEntitiesByRow(y / g.getTileSize());

        for (int i = 0; i < testable.size(); i++) {
            Entity e = testable.get(i);
            if (getBounds().intersects(e.getBounds())) {
                escape = true;
                break;
            }
        }

        if (physics.isGrounded()) {
            if (escape) {
                if (canJump) {
                    physics.applyForce(jumpForce);
                    canJump = false;
                } else {
                    physics.applyForce(movingForce);
                    canJump = true;
                }
            } else {
                physics.applyForce(normalJump);
            }
        }

        physics.update();
        x = (int) physics.getX();
        y = (int) physics.getY();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (isJumping()) {
            g.setColor(Color.red);
            g.drawString(Integer.toString(effectManager.getByTag("JUMP").getLifetime()), x + 24, y - 10);
        }

        g.setColor(Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((effectManager.contains("JUMP")) ? (int) physics.getX() : x, row * g.getTileSize(), width, height);
    }

    @Override
    public void decreaseHealth(Projectile p) {
        if (isJumping()) {
            StatusEffect se = effectManager.getByTag("JUMP");
            ((JumpEffect) se).update(p.getDamage());
        } else {
            super.decreaseHealth(p);
        }
    }

    @Override
    public void decreaseHealth(int damage) {
        if (isJumping()) {
            StatusEffect se = effectManager.getByTag("JUMP");
            ((JumpEffect) se).update(damage);
        } else {
            super.decreaseHealth(damage);
        }
    }

    @Override
    public double getSpeed() {
        if (isJumping()) {
            return normalJump.x;
        } else {
            return super.getSpeed();
        }
    }

    @Override
    public void editSpeed(double percentage) {
        if (isJumping()) {
            if (Math.ceil(percentage) != 0) {
                multiplier = Math.min(multiplier, percentage);
                physics.reduceGravity(0.8);
            }
        } else {
            super.editSpeed(percentage);
        }
    }

    public void setStats() {
        defaultSpeed = SPEED;
        damage = DAMAGE;
        y = physics.getGround();
    }
}
