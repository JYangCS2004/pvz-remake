package model;

import java.awt.*;

public class Sun extends Entity {
    boolean hasClicked = false;

    public Sun(int x, int y) {
        super(x, y);
        super.speed = 1;
        width = 30;
        height = 30;
    }

    @Override
    public void draw(Graphics g) {
        if (!hasClicked) {
            g.setColor(Color.yellow);
            g.fillRoundRect(x, y, width, height, 25, 25);
            g.setColor(Color.white);
        }
    }

    @Override
    public void update() {
        y += speed;
    }
}
