package model.projectiles.Bullet;

import model.Entity;
import model.ImageLibrary;
import model.physics.Vector;
import ui.GamePanel;

import java.awt.*;

public class CornCob extends Bullet {
    private int wait = 20;
    private Vector destination;
    private static int SPEED = 5;
    public CornCob(int x, int y, Entity owner, GamePanel g) {
        super(x, y, 0, 0, SPEED, 0, 100000, owner, g);
    }

    @Override
    public void draw(Graphics g) {
        if (wait <= 0) {
            Image temp = ImageLibrary.getImage("src/Graphics/corncob_rot.png", 2 * this.g.getTileSize(), 2 * this.g.getTileSize() * 78 / 162);
            g.drawImage(temp, x, y, null);
        } else {
            Image temp = ImageLibrary.getImage("src/Graphics/corncob.png", 2 * this.g.getTileSize(), 2 * this.g.getTileSize() * 78 / 162);
            g.drawImage(temp, x, y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, -1, -1);
    }

    @Override
    public void update() {

        if (y <= 0) {
            if (wait <= 0) {
                y++;
            } else {
                wait--;
            }
        } else {
            y -= SPEED;
        }
    }
}
