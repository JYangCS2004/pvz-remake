package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.projectiles.AOE.Beams.WinterMelonSplash;

public class WinterMelonSplashEffect extends StatusEffect {

    static final int LIFE_TIME = 1;
    static final String TAG = "WMELON";

    public WinterMelonSplashEffect(Entity host) {
        super(host, LIFE_TIME, TAG);
    }

    @Override
    public void update() {
        host.getGamePanel().getAOEManager().spawn(new WinterMelonSplash(host.getX(), host.getY(), host.getGamePanel()));
        lifetime--;
    }
}
