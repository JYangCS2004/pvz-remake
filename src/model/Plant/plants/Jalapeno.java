package model.Plant.plants;

import model.Entity;
import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BeamManager;
import model.projectiles.AOE.Beams.PepperFire;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class Jalapeno extends Plant {
    final static int HEALTH = 10000000;
    final static int COST = 125;
    final static String TAG = "ja";
    private final BeamManager projectileManager;
    private int counter = 10;

    public Jalapeno(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getAOEManager();
        canBeEaten = false;
    }

    @Override
    public void update() {
        super.update();
        if (counter == 0){
            projectileManager.spawn(new PepperFire(y, this, g));

            List<Entity> entities = g.getPlantManager().getEntitiesByRow(row);

            for (Entity e : entities) {
                if (((Plant) e).getTag().equals("it")) {
                    g.getPlantManager().remove(e);
                }
            }
        }
        else{
            counter--;
        }
    }

    @Override
    public void draw(Graphics g) {
        super.drawWithoutText(g);
        g.setColor(Color.red);
        g.drawString(Integer.toString(counter), x + this.g.getImageLibrary().getTextXFix(tag), y + 20);
        g.setColor(Color.white);
    }
}
