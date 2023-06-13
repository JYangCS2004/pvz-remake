package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;

public class NothingEffect extends StatusEffect {
    static final int LIFE_TIME = 1;
    static final String TAG = "NOTHING";

    public NothingEffect(Entity host) {
        super(host, LIFE_TIME, TAG);
    }

    @Override
    public void update() {
        lifetime--;
    }
}
