package ui;

import java.awt.*;

public class Card {
    boolean canPlant;
    private int cooldownTime;
    private int counter;
    private int height;
    private int width;
    private int decrement = 2;
    private String tag;
    private GamePanel g;

    public Card(String tag, int cooldownTime, GamePanel g) {
        this.cooldownTime = cooldownTime;
        canPlant = true;
        counter = cooldownTime;
        width = g.getTileSize();
        height = 0;
        this.g = g;
        this.tag = tag;
    }

    public void draw(Graphics g, int posX) {
        if (height <= 0) {
            canPlant = true;
        }

        if (counter == 0) {
            height -= decrement;
            counter = cooldownTime;
        } else {
            counter--;
        }

        g.setColor(new Color(0, 0, 0, 125));
        g.fillRoundRect(posX * this.g.getTileSize(), 0, width, height, 10, 10);
    }

    public String getTag() {
        return tag;
    }

    public boolean isCanPlant() {
        return canPlant;
    }

    public void resetHeight() {
        height = g.getTileSize();
    }
}
