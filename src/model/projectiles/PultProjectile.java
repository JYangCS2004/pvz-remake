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

    private double acceleration = 1;
    private double velocityY;
    private double velocityX;
    private int initialPos;
    private Entity target;

    public PultProjectile(int x, int y, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, 20, DAMAGE, 10000, g);
        time = 0;
        maxHeight = 50;
        initialPos = y;

        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());

        int minDist = Integer.MAX_VALUE;

        getMinDistance(testable, minDist);
        double speed = 4.5;
        velocityY = speed * Math.sin(Math.PI / 2 - initialAngle);
        velocityX = speed * Math.cos(initialAngle);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, width, height);
        g.setColor(Color.white);
    }

    public void update() {
        y -= velocityY;
        x += velocityX;

        if (counter == 0) {
            velocityY -= acceleration;
            counter = 12;
        } else {
            counter--;
        }
    }

    private void getMinDistance(List<Entity> testable, int minDist) {
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
     */
}
