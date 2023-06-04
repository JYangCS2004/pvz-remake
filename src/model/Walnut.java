package model;

import ui.GamePanel;

public class Walnut extends Plant{

    static final int health = 50;
    static final String tag = "wn";

    public Walnut(int x, int y, GamePanel g){
        super(x,y, health, tag, g);
    }

    @Override
    public void update() {}
}
