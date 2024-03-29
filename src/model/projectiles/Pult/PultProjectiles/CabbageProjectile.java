package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import model.projectiles.Pult.Pult;
import ui.GamePanel;

import java.awt.*;


public class CabbageProjectile extends Pult {

    final static int WIDTH = 16;
    final static int HEIGHT = 16;
    final static int DAMAGE = 20;



    public CabbageProjectile(int x, int y, Entity owner, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, DAMAGE, owner, g);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.g.getImageLibrary().cabbage,
                (int)super.x, (int)super.y, null);
        //g.setColor(Color.green);
        //g.fillOval((int)this.x, (int)this.y, width, height);
        g.setColor(Color.white);
    }

    /*private int getMinDistance(List<Entity> testable) {
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
        /*int distance = 10000;
        int miniDist = distance;
        for (Entity e : testable) {
            Zombie z = (Zombie) e;
            distance = e.getX() - super.x + (int)((double)100*e.getSpeed()) + g.getTileSize()/2;
            if(distance < 20){
                distance = 80;
            }
            if (distance < miniDist) {
                miniDist = distance ;
                target = z;
            }
        }
        return miniDist;
    } */

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
