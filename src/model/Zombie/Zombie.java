package model.Zombie;

import model.Entity;
import model.Plant.Plant;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public abstract class Zombie extends Entity {
    protected int curSpeed;
    private int defaultSpeed;
    private int health;

    private final int eatTime;

    private int damage;
    private int counter;

    public Zombie(int x, int y, int speed, int damage, int health, int eatTime,
                  int width, int height, GamePanel g) {
        super(x, y);
        super.g = g;
        this.curSpeed = speed;
        this.defaultSpeed = speed;
        this.damage = damage;
        this.health = health;
        this.eatTime = eatTime;
        this.counter = 5;
        super.width = width;
        super.height = height;

        super.row = y / g.getTileSize();
    }

    @Override
    public void draw(Graphics g) {
        g.fillRoundRect(x, y, 48, 48, 25, 25);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        g.setColor(Color.white);
    }

    public void update() {
        List<Entity> testable = g.getPlantManager().getEntitiesByRow(row);

        boolean isCollided = false;

        for (Entity entity : testable) {
            Plant p = (Plant) entity;
            if (p.getBounds().intersects(getBounds())) {
                counter--;
                curSpeed = 0;
                isCollided = true;
                if (counter == 0) {
                    p.decreaseHealth(damage);
                    counter = eatTime;
                }

                if (p.getHealth() <= 0) {
                    g.getPlantManager().freeSquare(p.getX()/ g.getTileSize(), p.getY() / g.getTileSize());
                    g.getPlantManager().remove(p);
                    curSpeed = getSpeed();
                }
            }
        }

        if (!isCollided) {
            curSpeed = defaultSpeed;
            // System.out.println("resume");
        }

        x += curSpeed;
    }

    public void decreaseHealth(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }
    public int getSpeed(){
        return curSpeed;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
