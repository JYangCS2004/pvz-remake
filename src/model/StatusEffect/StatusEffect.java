package model.StatusEffect;

import model.Entity;

public abstract class StatusEffect {

    protected int lifetime;
    protected Entity host;
    protected final String tag;

    public StatusEffect(Entity host, int lifetime, String tag){
        this.host = host;
        this.lifetime = lifetime;
        this.tag = tag;
    }

    public abstract void update();
    public String getTag(){
        return tag;
    }
    public boolean expired(){
        return lifetime <= 0;
    }

    public int getLifetime() {
        return lifetime;
    }
}
