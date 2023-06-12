package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class StunEffect extends StatusEffect {
    private static final int LIFETIME = 140;
    private static final String TAG = "STUN";

    public StunEffect(Entity host) {
        super(host, LIFETIME, TAG);
    }

    @Override
    public void update() {
        ((Zombie) host).editSpeed(0);
        if(this.lifetime == 1){
            ((Zombie) host).editSpeed(1);
        }
        lifetime--;
    }
}