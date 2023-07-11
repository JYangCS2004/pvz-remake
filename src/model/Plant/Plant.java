package model.Plant;

import model.Entity;
import ui.GamePanel;

import java.awt.*;
import java.awt.geom.Area;

public abstract class Plant extends Entity {

    protected int health;
    private final int cost;
    protected String tag;

    public Plant(int x, int y, int health, String tag, GamePanel g, int cost) {
        super(x, y);
        super.g = g;
        this.health = health;
        this.tag = tag;
        speed = 0;
        width = g.getTileSize();
        height = g.getTileSize();
        this.cost = cost;

        super.row = y / g.getTileSize();
    }

    public boolean mouseOver(int x, int y) {
        return new Area(getBounds()).contains(x, y);
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

    public void drawWithoutText(Graphics g){
        g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white);
    }

    public void draw(Graphics g, String tag){
        g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health),
                x + this.g.getImageLibrary().getTextXFix(tag),
                y + this.g.getImageLibrary().getTextYFix(tag));
    }

    public void drawWithoutText(Graphics g, String tag){
        g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white);
    }

    @Override
    public abstract void update();

    public void decreaseHealth(int damage){
        this.health-= 1;
    }

    public int getHealth() {
        return this.health;
    }

    public int getCost() {
        return cost;
    }

    public String getTag(){
        return tag;
    }
    public String getPlantCondition(){
        return "na";
    }

    public void setTimer(){}

}
