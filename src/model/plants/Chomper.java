package model.plants;

import model.Entity;
import model.Plant;
import ui.GamePanel;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class Chomper extends Plant {
    private final int eatingTime = 200;
    private static int health = 40;
    private static int COST = 150;
    private static String TAG = "ch";
    private int counter = 0;

    public Chomper(int x, int y, GamePanel g) {
        super(x, y, health, TAG, g, COST);
    }

    @Override
    public void update() {
        if (counter == 0) {
            List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(y / g.getTileSize());
            if (testable != null) {
                Iterator<Entity> it = testable.iterator();

                while (it.hasNext()) {
                    Entity e = it.next();
                    if (e.getBounds().intersects(getBounds())) {
                        counter = eatingTime;
                        it.remove();
                        break;
                    }
                }
            }
        } else {
            counter--;
        }
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
