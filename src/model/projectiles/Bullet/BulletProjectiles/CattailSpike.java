package model.projectiles.Bullet.BulletProjectiles;

import model.Entity;
import model.physics.Vector;
import model.projectiles.Bullet.Bullet;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CattailSpike extends Bullet {
    private static final int LIFETIME = 1;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int DAMAGE = 2;

    private double x;
    private double y;
    private Vector velocity;
    private Vector acceleration;
    private double maxSpeed;
    private double maxForce;
    private int followDelay = 50;
    private Entity target;
    private double radius;
    private double angle = 100;
    private double imageAngle = 120;
    private double quadrant = 0;

    private int a, b;

    public CattailSpike(int x, int y, Entity owner, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, 5, DAMAGE, LIFETIME, owner,  g);
        this.x = x;
        this.y = y;

        maxSpeed = 2.5;
        velocity = new Vector(1, 0);
        velocity.mult(maxSpeed);

        acceleration = new Vector(0, 0);

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
        double random = Math.toRadians(angle);
        List<Entity> testable = g.getZombieSpawner().getEntities();
        double distance = (new Point2D.Double(x, y)).distance(target.getX(), target.getY());
        double nextX = 0, nextY = 0;

        /*if (super.y < target.getY()) {
            //this.x -= Math.cos(random);
            //this.y -= Math.sin(random);
            imageAngle++;

            nextX = x - distance * Math.cos(random);
            nextY = y - distance * Math.sin(random);
        } else if (super.y == target.getY()) {
            this.x += 1;
        } else {
            //this.x -= Math.cos(random);
            //this.y += Math.sin(random);
            nextX = x + distance * Math.cos(random);
            nextY = y - distance * Math.sin(random);

            imageAngle = Math.toDegrees(Math.atan((nextY - y) / (nextX - x))) + 150;
        } */

        double normalize = (new Point2D.Double(x, y)).distance(nextX, nextY);

        // velocityX = (target.getX() - x) / (distance);
        /// velocityY = (target.getY() - y) / (distance);


        //x += 1;


            /* if (followDelay <= 0) {
                x += velocityX * 2;
                y += velocityY * 2;
                angle++;
            } else {
                followDelay--;
                //x+=2;
            } */

        if (!testable.contains(target)) {
            if (x > g.getScreenWidth() || x < 0 || y < 0 || y > g.getScreenHeight()) {
                lifetime--;
            }
        } else {
            seek();
            velocity.add(acceleration);
            velocity.limit(maxSpeed);
        }

        x = x + velocity.x;
        y = y + velocity.y;

        acceleration.mult(0);
    }


    public void seek() {
        Vector desired = Vector.sub(new Vector(target.getX() + 24, target.getY() + 24), new Vector(x, y));
        desired.normalize();
        desired.mult(maxSpeed);

        Vector steer = Vector.sub(desired, velocity);
        steer.limit(0.04);

        acceleration.add(steer);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform backup = g2.getTransform();

        BufferedImage spike;
        try {
            spike = ImageIO.read(new File("src/Graphics/spike3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //AffineTransform tx = AffineTransform.getRotateInstance(Math.PI / 180.0 * (angle - 150), 0, 0);

       // g2.setTransform(tx);
        //g2.drawImage(spike, (int)x, (int)y, null);
        //g2.setTransform(backup);

        double angle = velocity.heading();

        // double angle = Math.toDegrees(velocity.heading());

        AffineTransform tx = AffineTransform.getRotateInstance(angle, spike.getWidth() / 2, spike.getHeight() / 2);
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

    public Entity getTarget() {
        return target;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, width, height);
    }
}
