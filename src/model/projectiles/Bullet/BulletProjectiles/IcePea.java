package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class IcePea extends Bullet {

    static final int width = 16;
    static final int height = 16;
    static final int speed = 5;
    static final int damage = 1;
    static final int lifetime = 320;

    public IcePea(int x, int y, Entity owner, GamePanel g){
        super(x, y, width, height, speed, damage, lifetime, owner, g);
        ArrayList<String> Effects = new ArrayList<>();
        Effects.add("CHILL");
        onHitEffects = Effects;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.blue);
        g.fillOval(x, y, width, height);
        g.setColor(Color.WHITE);
    }
}
