package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import model.Zombie.Zombie;
import model.projectiles.Pult.Pult;
import ui.GamePanel;

import java.awt.*;
import java.util.List;


public class CabbageProjectile extends Pult {

    final static int WIDTH = 16;
    final static int HEIGHT = 16;
    final static int DAMAGE = 2;

    //private double acceleration = 1;
    static final double ACCELERATION = 0.12;
    //private double velocityY;
    private double velocityY = 6;
    final double velocityX;
    private int initialPos;
    private Zombie target = null;

    private double x;
    private double y;

    public CabbageProjectile(int x, int y, Entity owner, GamePanel g, int row) {
        super(x, y, WIDTH, HEIGHT, 20, DAMAGE, 100, row, owner, g);
        this.x = (double) x;
        this.y = (double) y;
        initialPos = y;

        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());

        int dist = getMinDistance(testable);
        //double speed = minDist;
        double speed = dist/(double)100;
        //velocityY = speed * Math.sin(Math.PI / 2 - initialAngle);
        //velocityX = speed * Math.cos(initialAngle);
        velocityX = speed;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval((int)this.x, (int)this.y, width, height);
        g.setColor(Color.white);
    }

    @Override
    public void update() {
        super.lifetime--;
        y -= velocityY;
        x += velocityX;
        velocityY -= ACCELERATION;
    }

    private int getMinDistance(List<Entity> testable) {
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
            Zombie z = (Zombie) e;
            distance = e.getX() - super.x + 100*e.getSpeed() + g.getTileSize()/2;
            if(distance < 20){
                distance = 80;
            }
            if (distance >= 0 && distance < miniDist) {
                miniDist = distance ;
                target = z;
            }
        }
        return miniDist;
    }

    public Zombie getTarget() {
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
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, width, height);
    }
}
