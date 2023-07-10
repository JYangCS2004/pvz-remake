package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import java.awt.*;

public class IceShroomFreeze extends Beam {

    //for now until effect is implemented
    //then its 0 and applies insta-kill
    static final int DAMAGE = 10;
    public IceShroomFreeze(Entity owner, GamePanel g){
        super(0, 0, g.getScreenWidth(), g.getScreenHeight(), DAMAGE, owner, true, g);
        this.onHitEffects.add("FREEZE");
        this.onHitEffects.add("IS_CHILL");
    }

    public void draw(Graphics g){
        g.setColor(Color.blue);
        System.out.println(x + " " + y);
        // g.fillRect(x, y, super.g.getTileSize()*3, super.g.getTileSize()*3);
        g.setColor(Color.white);
    }
}
