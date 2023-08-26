package model.Plant.plants;

import model.Plant.Plant;
import ui.GamePanel;

import java.awt.*;

public class IceTile extends Plant {
    final static int HEALTH = 1000;
    final static String TAG = "it";
    private int opacity = 255;

    public IceTile(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, 0);
        canBeEaten = false;
        disabled = true;
    }

    @Override
    public void update() {
        health--;
        if(health == 0){
            g.getPlantManager().remove(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (health < 250) {
            opacity -= 15;
            if (opacity < 0) {
                opacity = 0;
            }
        }
        g.setColor(new Color(0, 255, 255, opacity));
        g.fillRoundRect(x, y, width, height, 5, 5);
        g.setColor(Color.white);
    }

    @Override
    public String getPlantCondition() {
        return "anti-plant";
    }
}
