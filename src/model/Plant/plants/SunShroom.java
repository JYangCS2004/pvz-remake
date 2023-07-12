package model.Plant.plants;

import model.Plant.Plant;
import model.Sun;
import ui.GamePanel;

import java.awt.*;

public class SunShroom extends Plant {

    static final int HEALTH= 10;
    static final int COST = 25;
    static final String TAG = "sh";
    static final int COOL_DOWN = 350;
    private int countDown = 1000;
    private int counter = COOL_DOWN;


    public SunShroom(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
    }

    @Override
    public void update() {
        super.update();
        if(counter <= 0){
            if(countDown == 0){
                g.getSunSpawner().forceSpawn(new Sun(x+10 , y+20, 0, 25));
            }else{
                g.getSunSpawner().forceSpawn(new Sun(x+10 , y+20, 0, 15));
            }
            counter = COOL_DOWN;
        }
        else{
            counter--;
        }
        if(countDown > 0){
            countDown--;
        }
    }

    @Override
    public void draw(Graphics g){
        if(countDown == 0){
            super.drawWithoutText(g, "bsh");
        }
        else{
            super.drawWithoutText(g, tag);
            g.setColor(Color.red);
            g.drawString(Integer.toString(countDown), x + this.g.getImageLibrary().getTextXFix(tag), y + 20);
            g.setColor(Color.white);
        }
    }
}
