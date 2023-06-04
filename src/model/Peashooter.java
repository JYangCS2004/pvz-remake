package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Peashooter extends Plant{

    private final static int SPAWN_TIME = 70;
    private RandSpawnManager projectileManager;
    private int health = 20;
    private Image image;
    public Peashooter(int x, int y, GamePanel g){
        super(x,y, g);
        this.projectileManager = new RandSpawnManager(SPAWN_TIME, g, RandSpawnManager.Type.BULLET);
        try{
            this.image = ImageIO.read(new File("src/Graphics/Peashooter.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.GREEN);
        //g.fillRoundRect(x, y, 48, 48, 35, 35);
        g.drawImage(image, x-20, y, null);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health), x + 15, y + 24);
        projectileManager.drawEach(g);
    }

    @Override
    public void drawWithoutText(Graphics g){
        g.drawImage(image, x-20, y, null);
    }
    @Override
    public void update() {
        projectileManager.spawn(new Projectile(x, y + 15, g));
        projectileManager.updateEach();
    }
    @Override
    public void decreaseHealth(){
        this.health--;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}