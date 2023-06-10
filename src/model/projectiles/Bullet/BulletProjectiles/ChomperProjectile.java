package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.Plant.plants.Chomper;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import java.awt.*;

public class ChomperProjectile extends Bullet {

    static final int width = 16;
    static final int height = 16;
    static final int speed = 85/3;
    static final int damage = 50;
    static final int lifetime = 4;
    static final Color color = new Color(0, 100, 0);

    public ChomperProjectile(int x, int y, Entity owner, GamePanel g){
        super(x, y, width, height, speed, damage, lifetime, owner, g);
    }

    @Override
    public void draw(Graphics g) {
    }
    public void resetTimer(){
        ((Chomper) owner).setTimer();
    }
}
