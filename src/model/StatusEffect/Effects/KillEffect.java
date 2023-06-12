package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class KillEffect extends StatusEffect {
    static final int LIFE_TIME = 1;
    static final String TAG = "KILL";

    public KillEffect(Entity host) {
        super(host, LIFE_TIME, TAG);
    }

    @Override
    public void update() {
        ((Zombie)host).kill();
        lifetime--;
    }
}
