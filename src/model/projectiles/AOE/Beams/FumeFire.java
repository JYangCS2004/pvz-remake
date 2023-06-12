package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class FumeFire extends Beam {
    private final static int DAMAGE = 10;

    public FumeFire(int x, int y, Entity owner, GamePanel g) {
        super(x+20, y, g.getTileSize() * 3, g.getTileSize() / 4, DAMAGE, owner, false, g);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
    }
}
