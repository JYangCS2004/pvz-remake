package model.Zombie.zombies;

import model.Entity;
import model.ImageLibrary;
import model.Plant.Plant;
import model.Plant.plants.IceTile;
import model.Zombie.Zombie;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class Zomboni extends Zombie {
    private final static double SPEED = -0.2;
    private final static int HEALTH = 300;

    private Image image;
    private int iceTilePos;

    public Zomboni(int x, int y, GamePanel g) {
        super(x, y, SPEED, 0, HEALTH, 100, 0, 0, g);
        width = (int) (2.5 * g.getTileSize());
        height = (int) (968.0 / 1200 * width);
        image = ImageLibrary.getImage("src/Graphics/zomboni.png", width, height);

        iceTilePos = y;
        int temp = y + g.getTileSize();
        super.y = temp - image.getHeight(null);
    }

    @Override
    public void draw(Graphics g) {
        g.drawString(Integer.toString(health), x + 40, y);
        g.drawImage(image, x, y, null);
    }

    @Override
    public void update() {
        effectManager.updateAll();
        if (x < g.getScreenWidth() - g.getTileSize()) {
            g.getPlantManager().spawn(new IceTile((x / g.getTileSize() + 1) * g.getTileSize(), iceTilePos, g));
        }

        x += defaultSpeed;
        if(x <= 0){
            g.getZombieSpawner().removeZombie(this);
            return;
        }

        List<Entity> testable = g.getPlantManager().getEntitiesByRow(row);
        for (Entity e : testable) {
            if (getBounds().intersects(e.getBounds())) {
                updateCounter();
                Plant p = (Plant) e;
                if (counter == 0 && p.canBeEaten()) {
                    p.kill();
                }
            }
        }
    }
}
