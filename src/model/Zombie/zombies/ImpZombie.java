package model.Zombie.zombies;

import model.physics.LocalPhysics;
import model.physics.Vector;
import ui.GamePanel;

public class ImpZombie extends DefaultZombie {
    private LocalPhysics p;

    public ImpZombie(int x, int y, GamePanel g, int ground) {
        super(x, y, g);
        p = new LocalPhysics(x, y, ground);

        Vector jump = new Vector(-x / 2.0, -300);
        jump.normalize();
        jump.mult(8);

        p.applyForce(jump);
        row = ground / g.getTileSize();
    }

    @Override
    public void update() {
        p.update();

        if (!p.isGrounded()) {
            x = (int) p.getX();
        }

        y = (int) p.getY();
        super.update();
    }
}
