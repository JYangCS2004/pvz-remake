package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FumeFire extends Beam {
    private final static int DAMAGE = 10;
    private final BufferedImage spore;

    public FumeFire(int x, int y, Entity owner, GamePanel g) {
        super(x+20, y, g.getTileSize() * 3, g.getTileSize() / 4, DAMAGE, owner, false, g);
        try {
            spore = ImageIO.read(new File("src/Graphics/Spore.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        lifetime = 1;

        onHitEffects.add("PIERCING");
    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < 4; i++){
            g.drawImage(spore, spore.getWidth()*i-15 + 10*i, y+5, null);
        }
    }
}
