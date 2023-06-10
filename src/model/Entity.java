package model;

import ui.GamePanel;

import java.awt.*;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;
    protected GamePanel g;

    protected int width;
    protected int height;

    protected int row;
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics g);

    public void update() {
        x += speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getSpeed(){
        return speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getRow() {
        return row;
    }
}
