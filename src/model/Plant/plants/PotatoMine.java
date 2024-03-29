package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BeamManager;
import model.Zombie.Zombie;
import model.projectiles.AOE.Beams.PotatoMineExplosion;
import ui.GamePanel;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PotatoMine extends Plant {
    final static int HEALTH = 10;
    final static int COST = 25;
    final static String TAG = "pm";
    private final BeamManager projectileManager;
    private int counter = 450;

    public PotatoMine(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getAOEManager();
    }

    @Override
    public void update() {
        super.update();
        if (counter == 0){
            List<Entity> testable = g.getZombieSpawner().getEntitiesByRow(row);
            Queue<Entity> entityQueue = new PriorityQueue<>(Comparator.comparingInt(Entity::getX));
            entityQueue.addAll(testable);

            for (Entity e : entityQueue) {
                if(!((Zombie) e).isJumping() && getBounds().intersects(e.getBounds())){
                    projectileManager.spawn(new PotatoMineExplosion(x, y, this, g));
                }
            }
        }
        else{
            counter--;
        }
    }

    @Override
    public void draw(Graphics g) {
        if(counter <= 0){
            super.drawWithoutText(g, tag);
        }
        else{
            super.drawWithoutText(g, "upm");
            g.setColor(Color.red);
            g.drawString(Integer.toString(counter), x + this.g.getImageLibrary().getTextXFix(tag), y + 20);
            g.setColor(Color.white);
        }
    }
}
