package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import model.projectiles.Pult.Pult;
import ui.GamePanel;

import java.awt.*;

public class CornProjectile extends Pult {
    final static int WIDTH = 16;
    final static int HEIGHT = 16;
    final static int DAMAGE = 2;

    public CornProjectile(int x, int y, Entity owner, GamePanel g, int row) {
        super(x, y, WIDTH, HEIGHT, DAMAGE, row, owner, g);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(251, 236, 93));
        g.fillOval((int)x, (int)y, width, height);
        g.setColor(Color.white);
    }
}
