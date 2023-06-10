package model.Zombie;

import model.EffectManager;
import model.Entity;
import model.Plant.Plant;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public abstract class Zombie extends Entity {
    protected double curSpeed;
    private double defaultSpeed;
    private int health;

    private final int eatTime;

    private int damage;
    private int counter;
    private double buffer = 0;
    private EffectManager effectManager;
    private double multiplier = 1;


    public Zombie(int x, int y, double speed, int damage, int health, int eatTime,
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
        this.effectManager = new EffectManager(g);
    }

    @Override
    public void draw(Graphics g) {
        if(effectManager.contains("CHILL")){
            g.setColor(Color.blue);
        }
        g.fillRoundRect(x, y, 48, 48, 25, 25);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        g.setColor(Color.white);
    }

    public void update() {
        effectManager.updateAll();
        if(x <= -g.getTileSize()){
            g.getZombieSpawner().removeZombie(this);
            return;
        }
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
            curSpeed = (defaultSpeed*multiplier);
            // System.out.println("resume");
        }
        buffer += curSpeed;
        if(Math.abs(buffer) >= 1){
            x += buffer;
            buffer = 0;
        }
    }

    public void decreaseHealth(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }
    @Override
    public double getSpeed(){
        return curSpeed;
    }
    public void editSpeed(double percentage){
        multiplier = percentage;
    }
    public EffectManager getEffectManager(){
        return effectManager;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
