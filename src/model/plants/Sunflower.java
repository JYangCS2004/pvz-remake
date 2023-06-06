package model.plants;

import model.Plant;
import model.Sun;
import ui.GamePanel;
import java.awt.*;

public class Sunflower extends Plant {

    static final int HEALTH= 20;
    static final int COST = 50;
    static final String TAG = "sf";
    static final int COOL_DOWN = 250;
    private int counter = COOL_DOWN;


    public Sunflower(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
    }

    @Override
    public void update() {
        if(counter <= 0){
            g.getSunSpawner().forceSpawn(new Sun(x+10 , y+20, 0));
            counter = COOL_DOWN;
        }
        else{
            counter--;
        }
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        g.setColor(Color.red);
        g.drawString(Integer.toString(counter), x + this.g.getImageLibrary().getTextXFix(tag), y + 15);
        g.setColor(Color.white);
    }
}
