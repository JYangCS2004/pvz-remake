package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.physics.Vector;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Star extends Bullet {
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int DAMAGE = 10;
    private static final int SPEED = 5;
    private double angle = 0.1;

    private BufferedImage star;
    Vector direction;

    public Star(int x, int y, Entity owner, GamePanel g, Line2D.Double line) {
        super(x, y, WIDTH, HEIGHT, SPEED, DAMAGE, 1, owner, g);
        direction = new Vector(line);
        direction.normalize();
        direction.mult(speed);

        try {
            star = ImageIO.read(new File("src/Graphics/star.png"));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        x += direction.x;
        y += direction.y;

        if (x < 0 || x > g.getScreenWidth() || y < 0 || y > g.getScreenHeight()) {
            lifetime--;
        }

        angle++;
    }

    @Override
    public void draw(Graphics g) {
        AffineTransform tx_ne = AffineTransform.getRotateInstance(Math.toRadians(angle), star.getWidth() / 2, star.getHeight() / 2);
        AffineTransformOp star_op = new AffineTransformOp(tx_ne, AffineTransformOp.TYPE_BILINEAR);

        g.drawImage(star_op.filter(star, null), x, y, null);
    }
}
