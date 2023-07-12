package model.StatusEffect.Effects;

import model.Entity;
import model.Plant.plants.Pumpkin;
import model.StatusEffect.StatusEffect;

public class AddedHealthEffect extends StatusEffect {
    private static String TAG = "AHP";
    private Pumpkin source;

    public AddedHealthEffect(Entity host, Pumpkin source) {
        super(host, -1, TAG);
        this.source = source;
    }

    @Override
    public void update() {

    }

    public Pumpkin getSource() {
        return source;
    }

    public void update(int damage) {
        source.decreaseHealth(damage);
    }

    @Override
    public boolean expired() {
        return source.getHealth() <= 0;
    }
}
