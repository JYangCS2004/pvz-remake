package model.Plant.plants;

import model.Plant.Plant;
import model.SpawnManager.ProjectileManager.ProjectileManagers.BeamManager;
import model.projectiles.AOE.Beams.IceShroomFreeze;
import ui.GamePanel;

import java.awt.*;

public class IceShroom extends Plant {
    final static int HEALTH = 10000000;
    final static int COST = 75;
    final static String TAG = "is";
    private final BeamManager projectileManager;
    private int counter = 10;

    public IceShroom(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        this.projectileManager = g.getAOEManager();
    }

    @Override
    public void update() {
        super.update();
        if (counter == 0){
            projectileManager.spawn(new IceShroomFreeze(this, g));
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
