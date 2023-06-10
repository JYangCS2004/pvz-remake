package model.StatusEffect.Effects;

import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;
import ui.GamePanel;

public class ChillEffect extends StatusEffect {
    static final int LIFE_TIME = 100;
    static final String TAG = "CHILL";

    public ChillEffect(Zombie host, GamePanel g){
        super(host, LIFE_TIME, TAG, g);
    }

    public void update(){
        ((Zombie) host).editSpeed(0.5);
        if(this.lifetime == 1){
            ((Zombie) host).editSpeed(1);
        }
        lifetime--;
    }

    public int getLifeTime(){
        return LIFE_TIME;
    }
}
