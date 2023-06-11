package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class UndoChillEffect extends StatusEffect {
    static final int LIFE_TIME = 2;
    static final String TAG = "UNCHILL";

    public UndoChillEffect(Entity host) {
        super(host, LIFE_TIME, TAG);
    }

    @Override
    public void update() {
        ((Zombie) host).editSpeed(1);
        if(this.lifetime == 1){
            ((Zombie) host).editSpeed(1);
        }
        lifetime--;
    }
}
