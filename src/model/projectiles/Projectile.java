package model.projectiles;

import model.Entity;
import ui.GamePanel;

import java.util.List;

public abstract class Projectile extends Entity {

    protected int damage;
    protected int lifetime;

    protected Entity owner;

    protected List<String> onHitEffects;

    public Projectile(int x, int y, int width, int height, int speed,
                      int damage, int lifetime, Entity owner, List<String> onHitEffects,
                      GamePanel g) {
        super(x, y);
        super.g = g;
        super.width = width;
        super.height = height;
        super.speed = speed;
        this.damage = damage;
        this.lifetime = lifetime;
        this.owner = owner;
        this.onHitEffects = onHitEffects;
    }


    public boolean expired(){
        return lifetime <= 0;
    }

    public int getDamage() {
        return damage;
    }

    public void update() {
        super.update();
        lifetime--;
    }
    public Entity getOwner(){
        return owner;
    }

    public List<String> getOnHitEffects(){
        return onHitEffects;
    }
}
