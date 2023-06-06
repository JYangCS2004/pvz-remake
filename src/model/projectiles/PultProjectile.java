package model.projectiles;

import model.Entity;
import model.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.List;


public class PultProjectile extends Projectile {

    private static int WIDTH = 16;
    private static int HEIGHT = 16;
    private static int DAMAGE = 2;
    private int minDistance;
    private int maxHeight;
    private double initialAngle;

    private int time;
    private int counter = 0;

    //private double acceleration = 1;
    static final double ACCELERATION = 0.24;
    //private double velocityY;
    private double velocityY = 12;
    private double velocityX;
    private int initialPos;
    private Entity target = null;

    public PultProjectile(int x, int y, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, 20, DAMAGE, 105, g);
        time = 0;
        maxHeight = 50;
        initialPos = y;

        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());

        int minDist = Integer.MAX_VALUE;

        int dist = getMinDistance(testable, minDist);
        //double speed = minDist;
        double speed = dist/100;
        //velocityY = speed * Math.sin(Math.PI / 2 - initialAngle);
        //velocityX = speed * Math.cos(initialAngle);
        velocityX = speed;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, width, height);
        System.out.println(x + " " + y);
        g.setColor(Color.white);
    }

    @Override
    public void update() {
        super.lifetime--;
        y -= velocityY;
        x += velocityX;
        velocityY -= ACCELERATION;
    }

    private int getMinDistance(List<Entity> testable, int minDist) {
        /*
        for (Entity e : testable) {
            int distance = e.getX() - super.x;
            if (distance >= 0 && distance < minDist) {
                minDist = distance;
                target = e;
            }
        }

        if (minDist < Integer.MAX_VALUE) {
            speed = 7;
            this.minDistance = minDist;
        }

        initialAngle = Math.atan(4.0 * maxHeight / minDistance);
         */
        int distance = 10000;
        int miniDist = distance;
        for (Entity e : testable) {
            distance = e.getX() - super.x + 100*e.getSpeed();
            if (distance >= 0 && distance < miniDist) {
                miniDist = distance ;
                target = e;
            }
        }
        return miniDist;
    }

    public Entity getTarget() {
        return target;
    }

    /*
    new arc formula:
    -(4h/y^2)x(x-y)
    where h = CONSTANT
    such that distance =
    use
    org.apache.commons.math4.legacy.analysis.integration.simpsonintegrator

    f(x) = integral(0, b) sqrt(1 -(4hx)/b^2 - (4h(x-b))/b^2)
    where h = height and b = distance to enemy
     */
}
