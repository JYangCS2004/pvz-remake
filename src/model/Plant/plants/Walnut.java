package model.Plant.plants;

import model.Plant.Plant;
import ui.GamePanel;

public class Walnut extends Plant {

    static final int HEALTH= 50;
    static final int COST = 50;
    static final String TAG = "wn";

    public Walnut(int x, int y, GamePanel g){
        super(x,y, HEALTH, TAG, g, COST);
    }

    @Override
    public void update() {}
}
