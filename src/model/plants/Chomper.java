package model.plants;

import model.Plant;
import model.RandSpawnManager;
import model.projectiles.ChomperProjectile;
import ui.GamePanel;

import java.awt.*;

public class Chomper extends Plant {
    private final int eatingtime = 500;
    final static int SPAWN_TIME = 5;
    final static int HEALTH = 40;
    final static int COST = 150;
    final static String TAG = "ch";
    private int counter = 0;

    private final RandSpawnManager projectileManager;

    public Chomper(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        this.projectileManager = new RandSpawnManager(SPAWN_TIME, g, RandSpawnManager.Type.BULLET);
    }

    @Override
    public void update() {
        if(projectileManager.isCollided()){
            counter = eatingtime;
        }

        if (counter <= 0) {
            projectileManager.spawn(new ChomperProjectile(x, y + 15, g));
        }
        else{
            counter--;
        }

        projectileManager.updateEach();

        /*
        if (counter == 0) {
            List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());
            if (testable != null) {
                Iterator<Entity> it = testable.iterator();

                while (it.hasNext()) {
                    Entity e = it.next();
                    if (e.getBounds().intersects(getBounds())) {
                        counter = eatingtime;
                        it.remove();
                        break;
                    }
                }
            }
        } else {
            counter--;
        }

         */
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (counter != 0) {
            g.setColor(Color.red);
            g.drawString(Integer.toString(counter), x + this.g.getImageLibrary().getTextXFix(tag), y + 20);
            g.setColor(Color.white);
        }
    }

    @Override
    public void decreaseHealth() {
        super.health--;
    }
}
