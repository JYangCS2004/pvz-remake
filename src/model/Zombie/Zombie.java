package model.Zombie;

import model.EffectManager;
import model.Entity;
import model.Plant.Plant;
import model.Plant.plants.Garlic;
import model.Plant.plants.HypnoShroom;
import model.Plant.plants.PlantZombie;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class Zombie extends Entity {
    private Plant eating;
    protected double curSpeed;
    protected double defaultSpeed;
    private boolean yeet = false;
    protected int health;

    protected final int eatTime;

    protected int damage;
    protected int counter;
    protected double buffer = 0;
    protected final EffectManager effectManager;
    protected double multiplier = 1;
    protected int killBlock = 0;
    private int direction;


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
        row = y / g.getTileSize();
        this.effectManager = new EffectManager(g);
        direction = new Random().nextInt(2);
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

        //g.setColor(new Color(255, 255, 255, 125));
        g.fillRoundRect(x, y, width, height, 25, 25);
        g.setColor(Color.black);
        g.drawString(Integer.toString(health), x + 10, y + 24);
        g.setColor(Color.white);
    }

    public void update() {
        //System.out.println("ran");
        if (eating != null) {
            if (eating.getHealth() <= 0 || g.getPlantManager().isInactive(eating)) {
                eating = null;
            }
        }

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
        Queue<Entity> queuedEntities = new PriorityQueue<>(new EntityComparator());

        queuedEntities.addAll(testable);

        boolean isCollided = false;

        for (Entity entity : queuedEntities) {
            Plant p = (Plant) entity;

            if (isJumping()) {
                continue;
            }

            boolean eatingCondition = eating == null || p == eating;

            if (eatingCondition && getBounds().intersects(p.getBounds()) && p.canBeEaten()) {
                updateCounter();
                curSpeed = 0;
                isCollided = true;
                if (eating == null) {
                    eating = p;
                }

                if (counter == 0) {
                    counter = (int) ((2 - multiplier) * eatTime);

                    if (!isJumping() && (p instanceof Garlic) && !p.hasShield()) {
                        if (y % g.getTileSize() == 0) {
                            p.decreaseHealth(damage);
                        }

                        yeet = true;
                    } else {
                        p.decreaseHealth(damage);
                        if (p instanceof HypnoShroom && p.getHealth() <= 0) {
                            g.getPlantManager().spawnWithoutRegister(new PlantZombie(getX(), getY(), getHealth(), -getDefaultSpeed(), getDamage(), getEatTime(), g));
                            kill();
                        }

                        yeet = false;
                    }
                }

                if (p.getHealth() <= 0) {
                    // eating = null;
                    g.getPlantManager().remove(p);
                    curSpeed = getSpeed();
                }

                break;
            }
        }

        if (yeet) {
            curSpeed = defaultSpeed;
        }

        if (!isCollided) {
            curSpeed = defaultSpeed*multiplier;
        }

        buffer += curSpeed;
        if(Math.abs(buffer) >= 1){
            if (yeet) {
                if (row == 1) {
                    y -= buffer;
                } else if (row == g.getScreenRowSize()) {
                    y += buffer;
                } else {
                    if (direction == 1) {
                        y -= buffer;
                    } else {
                        y += buffer;
                    }
                }

                if (y % g.getTileSize() == 0) {
                    row = y / g.getTileSize();
                    direction = new Random().nextInt(2);
                    yeet = false;
                }
            } else {
                x += buffer;
            }
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

    public boolean isJumping() {
        return effectManager.contains("JUMP");
    }

    private static class EntityComparator implements Comparator<Entity> {

        @Override
        public int compare(Entity o1, Entity o2) {
            return Integer.compare(o2.getX(), o1.getX());
        }
    }
}
