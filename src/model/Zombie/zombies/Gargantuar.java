package model.Zombie.zombies;

import model.Entity;
import model.Plant.Plant;
import model.Zombie.Zombie;
import ui.GamePanel;

import java.util.List;

public class Gargantuar extends Zombie {
    private static double SPEED = -0.5;
    private static int HEALTH = 2000;
    private int initialPos;
    private boolean kids;

    private int walkDelay = 2;
    private int kidDelay = 20;

    public Gargantuar(int x, int y, GamePanel g) {
        super(x, y, SPEED, 0, HEALTH, 150, 0, 0, g);
        width = (int) (1.5 * g.getTileSize());
        height = width;
        initialPos = y;

        int temp = this.y + g.getTileSize();
        this.y = temp - height;
        killBlock = 1;
        counter = eatTime;
    }

    @Override
    public void update() {
        walkDelay--;
        effectManager.updateAll();

        if(x <= -g.getTileSize()){
            g.getZombieSpawner().removeZombie(this);
            return;
        }

        if (!kids && health < HEALTH / 2) {
            if (kidDelay == 0) {
                g.getZombieSpawner().forceSpawn(new ImpZombie(x, y, g, initialPos));
                kids = true;
            } else {
                kidDelay--;
            }
        }

        boolean collided = false;

        List<Entity> testable = g.getPlantManager().getEntitiesByRow(row);
        for (Entity e : testable) {
            if (getBounds().intersects(e.getBounds()) && ((Plant) e).canBeEaten()) {
                collided = true;
                curSpeed = 0;
                updateCounter();
                Plant p = (Plant) e;
                if (counter == 0) {
                    counter = (int) ((2 - multiplier) * eatTime);
                    p.kill();
                }
            }
        }

        if (!collided) {
            curSpeed = defaultSpeed * multiplier;
        }

        buffer += curSpeed;
        if (walkDelay == 0) {
            x += buffer;
            if (multiplier < 1) {
                walkDelay = 3;
            } else {
                walkDelay = 2;
            }
        }

        buffer = 0;
    }
}
