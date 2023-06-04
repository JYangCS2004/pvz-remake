package model;

import ui.GamePanel;
import java.awt.*;

public abstract class Plant extends Entity {

    private int health;
    private String tag;

    public Plant(int x, int y, int health, String tag, GamePanel g) {
        super(x, y);
        super.g = g;
        this.health = health;
        this.tag = tag;
        speed = 0;
        width = g.getTileSize();
        height = g.getTileSize();
    }

    public void draw(Graphics g){
        g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health),
                x + this.g.getImageLibrary().getTextXFix(tag),
                y + this.g.getImageLibrary().getTextYFix(tag));
    }
    @Override
    public abstract void update();
    public void decreaseHealth(){
        this.health--;
    }
    public int getHealth() {
        return this.health;
    }
}
