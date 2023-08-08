package model.StatusEffect.Effects;

import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class ChillEffect extends StatusEffect {
    static final int LIFE_TIME = 100;
    static final String TAG = "CHILL";
    static final double PERCENT = 0.5;

    public ChillEffect(Zombie host){
        super(host, LIFE_TIME, TAG);
    }

    public ChillEffect(Zombie host, int life_time){super(host, life_time, TAG);}

    @Override
    public void update(){
        ((Zombie) host).editSpeed(PERCENT);
        lifetime--;
    }

}
