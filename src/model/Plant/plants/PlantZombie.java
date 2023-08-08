package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.Zombie.Zombie;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class PlantZombie extends Plant {
    private final int eatTime;
    private final int damage;
    private final double defaultSpeed;
    private double speed;
    private int counter;
    private double buffer = 0;
    private static final String TAG = "pz";
    public PlantZombie(int x, int y, int health, double speed, int damage, int eatTime, GamePanel g){
        super(x,y, health, TAG, g, 0);
        defaultSpeed = speed;
        this.speed = speed;
        this.damage = damage;
        this.eatTime = eatTime;
        counter = eatTime;
        row = y / g.getTileSize();
        System.out.println(speed);
    }

    @Override
    public void update() {
        List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(row);

        boolean isCollided = false;

        for (int i = 0 ; i < testable.size(); i++) {
            Zombie z = (Zombie) testable.get(i);
            if (z.getBounds().intersects(getBounds())) {
                speed = 0;
                isCollided = true;
                if (counter <= 0) {
                    z.decreaseHealth(damage);
                    counter = eatTime;
                }

                if (z.getHealth() <= 0) {
                    z.kill();
                    speed = defaultSpeed;
                }
            }
        }

        if (!isCollided) {
            speed = defaultSpeed;
            // System.out.println("resume");
        }
        buffer += speed;
        if(Math.abs(buffer) >= 1){
            x += buffer;
            buffer = 0;
        }
        counter--;

        if (health <= 0 || x >= g.getScreenWidth()){
            g.getPlantManager().directRemove(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(139,0,139));
        g.fillRoundRect(x, y, 48, 48, 25, 25);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health), x + 10, y + 24);
        g.setColor(Color.white);
    }


}
