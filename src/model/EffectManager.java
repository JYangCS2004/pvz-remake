package model;

import model.StatusEffect.Effects.ChillEffect;
import model.StatusEffect.Effects.StunEffect;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;
import ui.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class EffectManager {

    protected GamePanel g;
    protected List<StatusEffect> effects;
    protected List<StatusEffect> removeQueue = new ArrayList<>();

    public EffectManager(GamePanel g){
        this.g = g;
        effects = new ArrayList<>();
    }

    public void add(StatusEffect e){
        removeByTag(e.getTag());
        effects.add(e);
    }

    public void checkEach(){
        for(StatusEffect e : effects){
            if(e.expired()){
                removeQueue.add(e);
            }
        }
    }

    public void updateAll(){
        checkEach();
        for(StatusEffect e: removeQueue){
            effects.remove(e);
        }
        removeQueue = new ArrayList<>();
        for(StatusEffect e: effects){
            e.update();
        }
    }

    public void removeByTag(String tag){
        for(StatusEffect e: effects){
            if(e.getTag().equals(tag)){
                removeQueue.add(e);
                return;
            }
        }
    }

    public boolean contains(String tag){
        for(StatusEffect e: effects){
            if(e.getTag().equals(tag)){
                return true;
            }
        }
        return false;
    }

    public StatusEffect select(Zombie z, String tag){
        switch (tag){
            case "STUN":
                return new StunEffect(z);
            default:
                return new ChillEffect(z);
        }
    }

    public int numOfEffects() {
        return effects.size();
    }

}
