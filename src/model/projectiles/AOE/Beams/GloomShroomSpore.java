package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class GloomShroomSpore extends Beam {
    private final static int DAMAGE = 15;

    public GloomShroomSpore(int x, int y, Entity owner, GamePanel g) {
        super(x-g.getTileSize()-1, y-g.getTileSize()-1, g.getTileSize() * 3+2, g.getTileSize()*3+2, DAMAGE, owner, false, g);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(230,230,250));
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
    }
}
