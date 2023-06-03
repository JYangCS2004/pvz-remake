package model;

import ui.GamePanel;

import java.awt.*;
import java.util.Queue;

public class Zombie extends Entity {
    private static final int SPEED = -1;
    private int health = 35;

    private final int eatTime = 30;
    private int counter = eatTime;

    public Zombie(int x, int y, GamePanel g) {
        super(x, y);
        super.g = g;
        speed = SPEED;
        super.width = g.getTileSize();
        super.height = g.getTileSize();
    }

    @Override
    public void draw(Graphics g) {
        g.fillRoundRect(x, y, 48, 48, 25, 25);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        g.setColor(Color.white);
    }

    public void update() {
        Queue<Entity> testable = g.getPlantManager().getEntitiesByRow(y / g.getTileSize());

        if (testable == null) {
            super.update();
            return;
        }

        Entity e = testable.peek();

        if (e != null) {
            Plant p = (Plant) e;
            if (p.getBounds().intersects(getBounds())) {
                counter--;
                speed = 0;
                if (counter == 0) {
                    p.decreaseHealth();
                    counter = eatTime;
                }

                if (p.getHealth() == 0) {
                    testable.remove(p);
                    speed = SPEED;
                }
            } else {
                speed = SPEED;
            }
        } else {
            speed = SPEED;
        }

        super.update();
    }

    public void decreaseHealth() {
        health -= 1;
    }

    public int getHealth() {
        return health;
    }
}
