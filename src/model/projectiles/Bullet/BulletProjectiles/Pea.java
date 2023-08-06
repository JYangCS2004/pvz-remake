package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.ImageLibrary;
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
    private Image pea;

    public Pea(int x, int y, Entity owner, GamePanel g){
        super(x, y, width, height, speed, damage, lifetime, owner, g);
        pea = ImageLibrary.getImage("src/Graphics/pea.png", 20, 20);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.drawImage(pea, x, y, null);
    }

}
