package model.projectiles;

import model.Entity;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class CattailSpike extends Projectile {
    private static final int LIFETIME = 1000;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int DAMAGE = 20;

    public CattailSpike(int x, int y, Entity owner, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, 0, DAMAGE, LIFETIME, owner, new ArrayList<>(), g);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
        g.setColor(Color.white);
    }
}
