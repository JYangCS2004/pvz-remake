package model.Plant.plants;

import model.Plant.Plant;
import ui.GamePanel;

import java.awt.*;

public class DoomShroom extends Plant {
    final static int HEALTH = 10000000;
    final static int COST = 125;
    final static String TAG = "ds";
    private int counter = 10;

    public DoomShroom(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
    }

    @Override
    public void update() {
        super.update();
        if (counter == 0){
            g.getPlantManager().spawn(new DoomShroomHole(x, y, g));
            // projectileManager.spawn(new DoomShroomExplosion(this, g));
            // g.getPlantManager().spawn(new DoomShroomHole(x, y, g));
            g.getPlantManager().remove(this);
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
