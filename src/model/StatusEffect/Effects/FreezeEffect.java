package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class FreezeEffect extends StatusEffect {
    private static final int LIFETIME = 200;
    private static final String TAG = "FREEZE";

    public FreezeEffect(Entity host) {
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
