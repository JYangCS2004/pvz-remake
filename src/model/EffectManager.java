package model;

import model.StatusEffect.Effects.*;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;
import ui.GamePanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EffectManager implements Iterable<StatusEffect> {

    protected GamePanel g;
    protected List<StatusEffect> effects;
    protected List<StatusEffect> removeQueue = new ArrayList<>();

    public EffectManager(GamePanel g){
        this.g = g;
        effects = new ArrayList<>();
    }

    public void add(StatusEffect e){

        if (e.getTag().equals("UNCHILL")) {
            removeByTag("CHILL");
            removeByTag("FREEZE");
        }

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

    public StatusEffect getByTag(String tag) {
        for(StatusEffect e: effects){
            if(e.getTag().equals(tag)){
                return e;
            }
        }
        return null;
    }

    public StatusEffect select(Zombie z, String tag){
        switch (tag){
            case "IS_CHILL":
                return new ChillEffect(z, 700);
            case "FREEZE":
                return new FreezeEffect(z);
            case "PIERCING":
                return new PierceEffect(z);
            case "KILL":
                return new KillEffect(z);
            case "FIRE":
                return new FireSplashEffect(z);
            case "UNCHILL":
                return new UndoChillEffect(z);
            case "STUN":
                return new StunEffect(z);
            default:
                return new ChillEffect(z);
        }
    }

    public int numOfEffects() {
        return effects.size();
    }

    public List<StatusEffect> getEffects(){
        return effects;
    }

    @Override
    public Iterator<StatusEffect> iterator() {
        return effects.iterator();
    }
}
