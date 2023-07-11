package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class CobDetonate extends Beam {
    public CobDetonate(int x, int y, Entity owner, GamePanel g) {
        super(x - g.getTileSize() / 2, y - g.getTileSize() / 2 , g.getTileSize()*3, g.getTileSize()*3, 0, owner, false, g);
        this.onHitEffects.add("KILL");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(128, 128, 0));
        g.fillRoundRect(x, y, width, height, 10, 10);
    }
}
