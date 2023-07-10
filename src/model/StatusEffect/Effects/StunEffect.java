package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class StunEffect extends StatusEffect {
    private static final int LIFETIME = 120;
    private static final String TAG = "STUN";

    public StunEffect(Entity host) {
        super(host, LIFETIME, TAG);
    }

    @Override
    public void update() {
        ((Zombie) host).editSpeed(0);
        if(this.lifetime == 1){
            ((Zombie) host).resetMultiplier();
        }
        lifetime--;
    }
}
