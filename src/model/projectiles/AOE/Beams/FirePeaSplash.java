package model.projectiles.AOE.Beams;

import model.Plant.plants.Peashooter;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class FirePeaSplash extends Beam {
    private final static int DAMAGE = 7;

    public FirePeaSplash(int x, int y, GamePanel g) {
        super(x, y+24, g.getTileSize(), g.getTileSize()/4, DAMAGE, new Peashooter(-200, -200, g), false, g);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
    }
}
