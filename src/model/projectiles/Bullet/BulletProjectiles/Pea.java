package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import java.awt.*;

public class Pea extends Bullet {

    static final int width = 16;
    static final int height = 16;
    static final int speed = 5;
    static final int damage = 10;
    static final int lifetime = 320;
    static final Color  color = new Color(0, 100, 0);

    public Pea(int x, int y, Entity owner, GamePanel g){
        super(x, y, width, height, speed, damage, lifetime, owner, g);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(Color.WHITE);
    }

}
