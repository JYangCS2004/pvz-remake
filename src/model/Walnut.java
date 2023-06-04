package model;

import ui.GamePanel;
import java.awt.*;

public class Walnut extends Plant{

    private int health = 50;
    public Walnut(int x, int y, GamePanel g){
        super(x,y, g);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRoundRect(x, y, 48, 48, 35, 35);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health), x + 15, y + 24);
    }
    @Override
    public void update() {}

    @Override
    public void decreaseHealth(){
        this.health--;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}
