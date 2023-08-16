package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class WinterMelon extends Melon {
    public WinterMelon(int x, int y, Entity owner, GamePanel g, int row) {
        super(x, y, owner, g, row);
        onHitEffects.remove("MELON");
        onHitEffects.add("WMELON");
        //winterMelon = ImageLibrary.getImage("src/Graphics/WinterMelon.png", 33, 28);
        try {
            melon = resize(ImageIO.read(new File("src/Graphics/WinterMelon.png")), 28);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
