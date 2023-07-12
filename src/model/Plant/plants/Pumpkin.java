package model.Plant.plants;

import model.Plant.Plant;
import ui.GamePanel;

import java.awt.*;

public class Pumpkin extends Plant {
    private static int HEALTH = 60;
    private static String TAG = "pk";
    private static int COST = 125;
    private boolean isEmpty;

    public Pumpkin(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        width += 2;
    }

    @Override
    public void draw(Graphics g) {
        if (isEmpty) {
            super.draw(g, tag);
        } else {
            super.draw(g, "fpk");
        }
    }

    @Override
    public Rectangle getBounds() {
        int tile = g.getTileSize();
        return new Rectangle(x + tile / 2 + 10, y, width, height / 2);
    }
}
