package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CattailSpike extends Bullet {
    private static final int LIFETIME = 10000;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int DAMAGE = 2;

    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private int followDelay = 50;
    private Entity target;
    private double radius;
    private double angle = 180;
    private double imageAngle = 120;
    private boolean turning = false;

    public CattailSpike(int x, int y, Entity owner, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, 5, DAMAGE, LIFETIME, owner,  g);
        this.x = x;
        this.y = y;

        double closest = 100000.0;
        List<Entity> zm = g.getZombieSpawner().getEntities();
        for (int i = 0; i < zm.size(); i++) {
            Entity e = zm.get(i);
            double temp = new Point((int)this.x, (int)this.y).distance(e.getX(), e.getY());

            if (temp < closest) {
                closest = temp;
                target = e;
            }
        }

        radius = (new Point((int)this.x, (int)this.y).distance(target.getX(), target.getY()));
    }


    @Override
    public void update() {

        double random = angle * Math.PI / 180.0;
        List<Entity> testable = g.getZombieSpawner().getEntities();

        if (super.y < target.getY()) {
            this.x += -Math.cos(random) * 2;
            this.y += -Math.sin(random) * 2;
            imageAngle++;
        } else if (super.y == target.getY()) {
            this.x += 1;
        } else {
            this.x += -Math.cos(random) * 2;
            this.y += Math.sin(random) * 2;
            imageAngle--;
        }

        if (testable.contains(target)) {

            double distance = (new Point((int)this.x, (int)this.y).distance(target.getX(), target.getY()));
            velocityX = (target.getX() - x) / (distance);
            velocityY = (target.getY() - y) / (distance);


            x += 1;
            x += velocityX * 2;
            y += velocityY * 2;


            if (followDelay <= 0) {
                angle++;
            } else {
                followDelay--;
            }

            /*if (turning) {
                imageAngle++;
            } else {
                if (imageAngle <= 0) {
                    turning = true;
                } else {
                    imageAngle--;
                }
            } */



        }
        else{
            lifetime = 50;
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform backup = g2.getTransform();

        BufferedImage spike;
        try {
            spike = ImageIO.read(new File("src/Graphics/spike_new.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //AffineTransform tx = AffineTransform.getRotateInstance(Math.PI / 180.0 * (angle - 150), 0, 0);

       // g2.setTransform(tx);
        //g2.drawImage(spike, (int)x, (int)y, null);
        //g2.setTransform(backup);

        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(imageAngle), spike.getWidth() / 2, spike.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
        g2.drawImage(op.filter(spike, null), (int)x, (int)y, null);
        //g.drawImage(rotate(spike, angle), (int)x, (int)y, null);
        g.setColor(Color.white);
    }

    private BufferedImage rotate(BufferedImage img, double angle) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(1000, 1000, img.getType());
        Graphics2D g2 = newImage.createGraphics();

        g2.rotate(Math.toRadians(angle - 150), 0, 0);
        g2.drawImage(img, null, 0, 0);
        return newImage;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, width, height);
    }
}
