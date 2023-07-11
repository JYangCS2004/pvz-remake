package model;

import java.awt.*;

public class Sun extends Entity {

    private final int value;
    public Sun(int x, int y, int speed) {
        super(x, y);
        super.speed = speed;
        width = 30;
        height = 30;
        value = 50;
    }

    public Sun(int x, int y, int speed, int value){
        super(x, y);
        super.speed = speed;
        width = 30;
        height = 30;
        this.value = value;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRoundRect(x, y, width, height, 25, 25);
        g.setColor(Color.white);
        /*if (!hasClicked) {
            g.setColor(Color.yellow);
            g.fillRoundRect(x, y, width, height, 25, 25);
            g.setColor(Color.white);
        }

         */
    }

    @Override
    public void update() {
        y += speed;
    }

    public int getValue(){return value;}
}
