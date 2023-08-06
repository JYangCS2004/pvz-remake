package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;

public class ShieldedEffect extends StatusEffect {
    private static final String TAG = "SHIELD";

    public ShieldedEffect(Entity host, int lifetime) {
        super(host, lifetime, TAG);
    }

    @Override
    public void update() {
    }

    public void update(int damage){
        lifetime -= damage;
    }
}
