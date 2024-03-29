package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;

public class PierceEffect extends StatusEffect {
    static final int LIFE_TIME = 1;
    static final String TAG = "PIERCING";

    private int damage;

    public PierceEffect(Entity host) {
        super(host, LIFE_TIME, TAG);
    }

    @Override
    public void update() {
        Zombie e = (Zombie) host;
        e.decreaseHealth(damage);

        if (e.getHealth() <= 0) {
            e.kill();
        }

        lifetime--;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
