package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.projectiles.AOE.Beams.MelonSplash;

public class MelonSplashEffect extends StatusEffect {

    static final int LIFE_TIME = 1;
    static final String TAG = "MELON";

    public MelonSplashEffect(Entity host) {
        super(host, LIFE_TIME, TAG);
    }

    @Override
    public void update() {
        host.getGamePanel().getAOEManager().spawn(new MelonSplash(host.getX(), host.getY(), host.getGamePanel()));
        lifetime--;
    }
}
