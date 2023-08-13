package model.projectiles.AOE.Beams;

import ui.GamePanel;

public class WinterMelonSplash extends MelonSplash {
    public WinterMelonSplash(int x, int y, GamePanel g) {
        super(x, y, g);
        onHitEffects.add("CHILL");
    }
}
