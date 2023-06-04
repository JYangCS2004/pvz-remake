package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Walnut extends Plant{

    private int health = 50;

    private Image image;
    public Walnut(int x, int y, GamePanel g){
        super(x,y, g);
        try{
            this.image = ImageIO.read(new File("src/Graphics/Walnut.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.ORANGE);
        //g.fillRoundRect(x, y, 48, 48, 35, 35);
        g.drawImage(image, x+7, y+4, null);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health), x + 20, y + 35);
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
