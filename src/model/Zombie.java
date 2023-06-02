package model;

import java.awt.*;

public class Zombie extends Entity {
    private static final int SPEED = -1;
    private int health = 10;
    public Zombie(int x, int y) {
        super(x, y);
        speed = SPEED;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, 48, 48);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        g.setColor(Color.white);
    }

    public void decreaseHealth() {
        health -= 1;
    }

    public int getHealth() {
        return health;
    }
}
