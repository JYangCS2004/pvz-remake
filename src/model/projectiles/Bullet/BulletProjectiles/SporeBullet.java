package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SporeBullet extends Bullet {
    static final int width = 16;
    static final int height = 16;
    static final int speed = 5;
    static final int damage = 10;
    private final BufferedImage spore;

    public SporeBullet(int x, int y,int lifetime, Entity owner, GamePanel g){
        super(x, y, width, height, speed, damage, lifetime, owner, g);
        try {
            spore = ImageIO.read(new File("src/Graphics/Spore.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(spore, x, y, null);
    }
}
