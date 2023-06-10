package model.projectiles.Bullet;

import model.Entity;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.util.ArrayList;

public abstract class Bullet extends Projectile {

    public Bullet(int x, int y, int width, int height, int speed, int damage, int lifetime, Entity owner, GamePanel g){
        super(x,y,width,height,speed,damage,lifetime, owner, new ArrayList(), g);
    }
}
