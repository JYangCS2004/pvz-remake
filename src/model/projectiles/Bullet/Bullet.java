package model.projectiles.Bullet;

import model.Entity;
import model.Plant.plants.TorchWood;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public abstract class Bullet extends Projectile {
    public boolean canCollide;
    protected TorchWood hitting;

    public Bullet(int x, int y, int width, int height, int speed, int damage, int lifetime, Entity owner, GamePanel g){
        super(x,y,width,height,speed,damage,lifetime, owner, new ArrayList<>(), g);
        canCollide = true;
    }

    public Rectangle getBounds() {
        return super.getBounds();
    }

    public void setHitting(TorchWood t) {
        hitting = t;
    }

    public TorchWood getHitting() {
        return hitting;
    }
}
