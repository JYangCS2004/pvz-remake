package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class PotatoMineExplosion extends Beam {
    static final int DAMAGE = 0;
    public PotatoMineExplosion(int x, int y, Entity owner, GamePanel g){
        super(x, y, g.getTileSize(), g.getTileSize(), DAMAGE, owner, true, g);
        this.onHitEffects.add("KILL");
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, this.g.getTileSize(), this.g.getTileSize());
        g.setColor(Color.white);
    }

}
