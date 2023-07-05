package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class CherryBombExplosion extends Beam {

    //for now until effect is implemented
    //then its 0 and applies insta-kill
    static final int DAMAGE = 0;
    public CherryBombExplosion(int x, int y, Entity owner, GamePanel g){
        super(x-g.getTileSize(), y-g.getTileSize(), g.getTileSize()*3, g.getTileSize()*3, DAMAGE, owner, true, g);
        this.onHitEffects.add("KILL");
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        System.out.println(x + " " + y);
        g.fillRect(x, y, super.g.getTileSize()*3, super.g.getTileSize()*3);
        g.setColor(Color.white);
    }
}
