package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import model.ImageLibrary;
import ui.GamePanel;

import java.awt.*;

public class WinterMelon extends Melon {

    private final Image winterMelon;

    public WinterMelon(int x, int y, Entity owner, GamePanel g, int row) {
        super(x, y, owner, g, row);
        onHitEffects.remove("MELON");
        onHitEffects.add("WMELON");
        winterMelon = ImageLibrary.getImage("src/Graphics/WinterMelon.png", 33, 28);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(winterMelon, (int)x, (int)y, null);
    }
}
