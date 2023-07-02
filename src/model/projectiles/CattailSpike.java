package model.projectiles;

import model.Entity;
import ui.GamePanel;

import java.awt.*;
import java.util.List;

public class CattailSpike extends Projectile {
    private static final int LIFETIME = 1000;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int DAMAGE = 20;

    public CattailSpike(int x, int y, int width, int height, int speed, int damage, int lifetime, Entity owner, List<String> onHitEffects, GamePanel g) {
        super(x, y, WIDTH, HEIGHT, speed, damage, lifetime, owner, onHitEffects, g);
    }

    @Override
    public void draw(Graphics g) {

    }
}
