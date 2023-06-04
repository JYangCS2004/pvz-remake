package model;

import ui.GamePanel;
import java.awt.*;

public abstract class Plant extends Entity {

    public Plant(int x, int y, GamePanel g) {
        super(x, y);
        super.g = g;
        speed = 0;
        width = g.getTileSize();
        height = g.getTileSize();
    }

    public abstract void drawWithoutText(Graphics g);
    @Override
    public abstract void update();
    public abstract void decreaseHealth();
    public abstract int getHealth();
}
