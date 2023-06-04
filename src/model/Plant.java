package model;

import ui.GamePanel;

public abstract class Plant extends Entity {

    public Plant(int x, int y, GamePanel g) {
        super(x, y);
        super.g = g;
        speed = 0;
        width = g.getTileSize();
        height = g.getTileSize();
    }

    @Override
    public abstract void update();
    public abstract void decreaseHealth();
    public abstract int getHealth();
}
