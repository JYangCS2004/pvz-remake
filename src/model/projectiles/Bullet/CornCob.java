package model.projectiles.Bullet;

import model.Entity;
import model.ImageLibrary;
import model.physics.Vector;
import model.projectiles.AOE.Beams.CobDetonate;
import ui.GamePanel;

import java.awt.*;

public class CornCob extends Bullet {
    private int wait = 20;
    private static int LIFETIME = 1;
    private Vector destination;
    private static int SPEED = 8;
    public CornCob(int x, int y, Entity owner, GamePanel g) {
        super(x, y, 0, 0, SPEED, 0, LIFETIME, owner, g);
    }

    @Override
    public void draw(Graphics g) {
        if (wait <= 0) {
            Image temp = ImageLibrary.getImage("src/Graphics/corncob_rot.png", 2 * this.g.getTileSize() * 78 / 162, 2 * this.g.getTileSize());
            g.drawImage(temp, x, y, null);
        } else {
            Image temp = ImageLibrary.getImage("src/Graphics/corncob.png", 2 * this.g.getTileSize() * 78 / 162, 2 * this.g.getTileSize());
            g.drawImage(temp, x, y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, -1, -1);
    }

    @Override
    public void update() {
        if (wait > 0) {
            if (y + g.getTileSize() * 2 > 0) {
                y -= SPEED;
            } else {
                wait--;
            }
        } else {
            x = (int) destination.x;
            y += SPEED;

            if (y > destination.y) {
                lifetime--;
                g.getAOEManager().spawn(new CobDetonate((int) destination.x, (int) destination.y, owner, g));
            }
        }
    }

    public void setDestination(int x, int y) {
        destination = new Vector(x, y);
    }
}
