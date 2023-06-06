package model;

import ui.GamePanel;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public abstract class Zombie extends Entity {
    protected int curSpeed;
    private int health;

    private final int eatTime;

    private int damage;
    private int counter;
    private boolean isCollided = false;

    public Zombie(int x, int y, int speed, int damage, int health, int eatTime,
                  int width, int height, GamePanel g) {
        super(x, y);
        super.g = g;
        this.curSpeed = speed;
        this.damage = damage;
        this.health = health;
        this.eatTime = eatTime;
        this.counter = 5;
        super.width = width;
        super.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRoundRect(x, y, 48, 48, 25, 25);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        g.setColor(Color.white);
    }

    public void update() {
        List<Entity> testable = g.getPlantManager().getEntitiesByRow(y / g.getTileSize());
        isCollided = false;
        if (testable == null || testable.isEmpty()) {
            x += getSpeed();
            return;
        }

        Iterator<Entity> it = testable.iterator();

        while (it.hasNext()) {
            Plant p = (Plant) it.next();
            if (p.getBounds().intersects(getBounds())) {
                counter--;
                curSpeed = 0;
                isCollided = true;
                if (counter == 0) {
                    p.decreaseHealth();
                    counter = eatTime;
                }

                if (p.getHealth() == 0) {
                    g.getPlantManager().freeSquare(p.x / g.getTileSize(), p.y / g.getTileSize());
                    it.remove();
                    curSpeed = getSpeed();
                }
            }
        }

        if (!isCollided) {
            curSpeed = getSpeed();
        }

        x += curSpeed;
    }

    public void decreaseHealth(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }
    public abstract int getSpeed();
}
