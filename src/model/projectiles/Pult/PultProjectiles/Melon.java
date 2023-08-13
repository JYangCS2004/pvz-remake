package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import model.ImageLibrary;
import model.projectiles.Pult.Pult;
import ui.GamePanel;

import java.awt.*;

public class Melon extends Pult {

    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    final static int DAMAGE = 25;
    private final Image melon;

    public Melon(int x, int y, Entity owner, GamePanel g, int row) {
        super(x, y, WIDTH, HEIGHT, DAMAGE, row, owner, g);
        onHitEffects.add("MELON");
        melon = ImageLibrary.getImage("src/Graphics/Melon.png", 33, 28);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(melon, (int)x, (int)y, null);
    }
}
