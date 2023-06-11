package model.projectiles.Pult;

import model.Entity;
import model.Zombie.Zombie;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Pult extends Projectile {
    private static final int LIFETIME = 100;
    private static final int SPEED = 20;

    //private double acceleration = 1;
    static final double ACCELERATION = 0.12;
    //private double velocityY;
    private double velocityY = 6;
    final double velocityX;

    protected double x;
    protected double y;

    public Pult(int x, int y, int width, int height, int damage, int row, Entity owner, GamePanel g){
        super(x,y,width,height,SPEED,damage, LIFETIME, owner, new ArrayList<>(),g);
        super.row = row;

        this.x =  x;
        this.y =  y;

        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());

        int dist = getMinDistance(testable);
        //double speed = minDist;
        //velocityY = speed * Math.sin(Math.PI / 2 - initialAngle);
        //velocityX = speed * Math.cos(initialAngle);
        velocityX = dist/(double)100;
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
            distance = e.getX() - super.x + (int)((double)100*e.getSpeed()) + g.getTileSize()/2;
            if(distance < 20){
                distance = 80;
            }
            if (distance < miniDist) {
                miniDist = distance ;
            }
        }
        return miniDist;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, width, height);
    }
}
