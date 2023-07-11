package model.Plant.plants;

import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BeamManager;
import model.projectiles.AOE.Beams.DoomShroomExplosion;
import ui.GamePanel;

import java.awt.*;

public class DoomShroomHole extends Plant {
    final static int HEALTH = 2000;
    final static String TAG = "dsh";
    private int counter = 120;

    private final BeamManager projectileManager;


    public DoomShroomHole(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, 0);
        this.projectileManager = g.getAOEManager();
    }

    @Override
    public void update() {
        if(counter == 120){
            projectileManager.spawn(new DoomShroomExplosion(this, g));
        }
        counter--;
        health--;
        if(health == 0){
            g.getPlantManager().remove(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.drawWithoutText(g, "dsh");
        if(counter > 0){
            super.drawWithoutText(g, "dse");
        }
        g.setColor(Color.red);
        g.drawString(Integer.toString(health), x + this.g.getImageLibrary().getTextXFix(tag), y + 20);
        g.setColor(Color.white);

    }

    @Override
    public boolean canBeEaten(){
        return false;
    }
}
