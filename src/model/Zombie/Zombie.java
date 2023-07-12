package model.Zombie;

import model.EffectManager;
import model.Entity;
import model.Plant.Plant;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public abstract class Zombie extends Entity {
    protected double curSpeed;
    private final double defaultSpeed;
    private int health;

    private final int eatTime;

    private final int damage;
    private int counter;
    private double buffer = 0;
    protected final EffectManager effectManager;
    private double multiplier = 1;
    protected int killBlock = 0;


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
        if(effectManager.contains("STUN")){
            g.setColor(Color.yellow);
        }
        else if(effectManager.contains("FREEZE")){
            g.setColor(Color.CYAN);
        }
        else if(effectManager.contains("CHILL")){
            g.setColor(Color.blue);
        }
        g.fillRoundRect(x, y, 48, 48, 25, 25);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 10, y + 24);
        g.setColor(Color.white);
    }

    public void update() {
        if (effectManager.numOfEffects() == 0) {
            multiplier = 1;
        } else {
            effectManager.updateAll();
        }

        if(x <= -g.getTileSize()){
            g.getZombieSpawner().removeZombie(this);
            return;
        }
        List<Entity> testable = g.getPlantManager().getEntitiesByRow(row);

        boolean isCollided = false;

        for (int i = 0 ; i < testable.size(); i++) {
            Plant p = (Plant) testable.get(i);
            if (p.getBounds().intersects(getBounds()) && p.canBeEaten()) {
                updateCounter();
                curSpeed = 0;
                isCollided = true;
                if (counter == 0) {
                    p.decreaseHealth(damage);
                    counter = eatTime;
                }

                if (p.getHealth() <= 0) {
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

    public void decreaseHealth(Projectile p) {
        health -= p.getDamage();
    }

    public void decreaseHealth(int damage){
        health -= damage;
    }

    public void updateCounter(){
        if(!effectManager.contains("STUN")){
            counter--;
        }
    }

    public int getHealth() {
        return health;
    }

    @Override
    public double getSpeed(){
        return curSpeed;
    }

    public double getDefaultSpeed(){return defaultSpeed;}
    public int getEatTime(){return eatTime;}
    public int getDamage(){return damage;}

    public void editSpeed(double percentage){
        multiplier = Math.min(percentage, multiplier);
    }

    public void resetMultiplier(){multiplier = 1;}

    public EffectManager getEffectManager(){
        return effectManager;
    }

    public void kill(){
        if(killBlock == 0){
            g.getZombieSpawner().removeZombie(this);
            return;
        }
        killBlock--;
        this.health -= 1800;
    }
}
