package model.projectiles;

import model.Projectile;
import ui.GamePanel;

import java.awt.*;

public class PeashooterProjectile extends Projectile {

    static final int width = 16;
    static final int height = 16;
    static final int speed = 5;
    static final int damage = 1;
    static final int lifetime = 320;
    static final Color  color = new Color(0, 100, 0);

    public PeashooterProjectile(int x, int y, GamePanel g){
        super(x, y, width, height, speed, damage, lifetime, g);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(Color.WHITE);
    }

}
