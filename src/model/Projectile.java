package model;

import ui.GamePanel;

public abstract class Projectile extends Entity {

    protected int damage;
    protected int lifetime;

    public Projectile(int x, int y, int width, int height, int speed, int damage, int lifetime, GamePanel g) {
        super(x, y);
        super.g = g;
        super.width = width;
        super.height = height;
        super.speed = speed;
        this.damage = damage;
        this.lifetime = lifetime;
    }

    public boolean expired(){
        return lifetime <= 0;
    }


    public void update() {
        super.update();
        lifetime--;
    }
}
