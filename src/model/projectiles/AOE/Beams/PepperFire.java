package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;
import java.awt.*;

public class PepperFire extends Beam {

    //for now until effect is implemented
    //then its 0 and applies insta-kill
    static final int DAMAGE = 50;
    public PepperFire(int y, Entity owner, GamePanel g){
        super(0, y, g.getScreenWidth(), g.getTileSize(), DAMAGE, owner, true, g);
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(0, y, this.g.getScreenWidth(), this.g.getTileSize());
        g.setColor(Color.white);
    }
}
