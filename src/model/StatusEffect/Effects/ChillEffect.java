package model.StatusEffect.Effects;

import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class ChillEffect extends StatusEffect {
    static final int LIFE_TIME = 100;
    static final String TAG = "CHILL";

    public ChillEffect(Zombie host){
        super(host, LIFE_TIME, TAG);
    }

    public ChillEffect(Zombie host, int life_time){super(host, life_time, TAG);}

    public void update(){
        ((Zombie) host).editSpeed(0.5);
        lifetime--;
    }

}
