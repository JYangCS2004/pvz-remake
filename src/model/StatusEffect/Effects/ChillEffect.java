package model.StatusEffect.Effects;

import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class ChillEffect extends StatusEffect {
    static final int LIFE_TIME = 100;
    static final String TAG = "CHILL";

    public ChillEffect(Zombie host){
        super(host, LIFE_TIME, TAG);
    }

    public void update(){
        ((Zombie) host).editSpeed(0.5);
        if(this.lifetime == 1){
            ((Zombie) host).editSpeed(1);
        }
        lifetime--;
    }

}
