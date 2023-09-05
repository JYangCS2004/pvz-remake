package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import model.projectiles.Pult.Pult;
import ui.GamePanel;

import java.awt.*;

public class ButterBlock extends Pult {
    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    final static int DAMAGE = 20;

    public ButterBlock(int x, int y, Entity owner, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, DAMAGE, owner, g);
        onHitEffects.add("STUN");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRoundRect((int)x, (int)y, width, height, 10, 10);
        g.setColor(Color.white);
    }
}
