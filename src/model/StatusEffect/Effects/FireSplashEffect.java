package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.projectiles.AOE.Beams.FirePeaSplash;

public class FireSplashEffect extends StatusEffect {

    static final int LIFE_TIME = 1;
    static final String TAG = "FIRE";

    public FireSplashEffect(Entity host) {
        super(host, LIFE_TIME, TAG);
    }

    @Override
    public void update() {
        host.getGamePanel().getAOEManager().spawn(new FirePeaSplash(host.getX(), host.getY(), host.getGamePanel()));
        lifetime--;
    }
}
