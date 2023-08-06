package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.ImageLibrary;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import java.awt.*;

public class FirePea extends Bullet {
    static final int width = 16;
    static final int height = 16;
    static final int speed = 5;
    static final int damage = 33;
    static final int lifetime = 320;
    static final Color  color = new Color(0, 100, 0);

    private Image firePea;

    public FirePea(int x, int y, Entity owner, GamePanel g) {
        super(x, y, width, height, speed, damage, lifetime, owner, g);
        onHitEffects.add("UNCHILL");
        onHitEffects.add("FIRE");
        firePea = ImageLibrary.getImage("src/Graphics/fire_pea.png", 25, 25);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.drawImage(firePea, x, y, null);
    }

}
