package model.StatusEffect;

import model.Entity;
import ui.GamePanel;

public abstract class StatusEffect {

    protected int lifetime;
    protected Entity host;
    protected final String tag;
    private final GamePanel g;

    public StatusEffect(Entity host, int lifetime, String tag, GamePanel g){
        this.host = host;
        this.lifetime = lifetime;
        this.tag = tag;
        this.g = g;
    }

    public abstract void update();
    public abstract int getLifeTime();
    public String getTag(){
        return tag;
    }
    public boolean expired(){
        return lifetime == 0;
    }

}
