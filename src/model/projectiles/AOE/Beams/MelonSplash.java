package model.projectiles.AOE.Beams;

import model.ImageLibrary;
import model.Plant.plants.Peashooter;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class MelonSplash extends Beam {

    private final static int DAMAGE = 15;
    private final Image melonSplash;

    public MelonSplash(int x, int y, GamePanel g) {
        super(x-g.getTileSize(), y-g.getTileSize(), g.getTileSize() * 3, g.getTileSize() * 3, DAMAGE, new Peashooter(-200, -200, g), false, g);
        melonSplash = ImageLibrary.getImage("src/Graphics/CrackedMelon.png", 50, 50);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(melonSplash, x+super.g.getTileSize(), y+super.g.getTileSize(), null);
    }
}
