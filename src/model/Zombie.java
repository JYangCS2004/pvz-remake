package model;

import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class Zombie extends Entity {
    private static final int SPEED = -1;
    private int health = 10;

    private final int eatTime = 30;
    private int counter = eatTime;
    public Zombie(int x, int y, GamePanel g) {
        super(x, y);
        super.g = g;
        speed = SPEED;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, 48, 48);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        g.setColor(Color.white);
    }


    public void update() {
        int row = y / g.getTileSize();
        List<Entity> testable = g.getPlantManager().getEntitiesByRow(row);

        if (testable == null || testable.size() == 0) {
            super.update();
            return;
        }

        Plant p = (Plant) testable.get(0);

        if (x <= p.getX() + g.getTileSize() && x >= p.getX()) {
            speed = 0;
            counter--;
            if (counter == 0) {
                p.decreaseHealth();
                counter = eatTime;
            }

            if (p.getHealth() == 0) {
                g.getPlantManager().removeByRow(p, row);
                speed = SPEED;
            }
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
