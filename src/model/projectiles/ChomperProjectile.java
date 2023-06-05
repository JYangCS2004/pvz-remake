package model.projectiles;

import model.Projectile;
import ui.GamePanel;

import java.awt.*;

public class ChomperProjectile extends Projectile {

    static final int width = 16;
    static final int height = 16;
    static final int speed = 85/3;
    static final int damage = 50;
    static final int lifetime = 3;
    static final Color color = new Color(0, 100, 0);

    public ChomperProjectile(int x, int y, GamePanel g){
        super(x, y, width, height, speed, damage, lifetime, g);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(Color.WHITE);
    }

}
