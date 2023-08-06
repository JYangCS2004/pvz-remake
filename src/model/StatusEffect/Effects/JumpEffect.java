package model.StatusEffect.Effects;

import model.Entity;
import model.StatusEffect.StatusEffect;
import model.Zombie.zombies.PogoZombie;

public class JumpEffect extends StatusEffect {
    private static final String TAG = "JUMP";
    // private static final int LIFETIME = 60;

    public JumpEffect(Entity host, int lifetime) {
        super(host, lifetime, TAG);
    }

    @Override
    public void update() {

    }

    public void update(int damage){
        lifetime -= damage;
    }

    @Override
    public boolean expired() {
        boolean expire = super.expired();
        if (expire && host instanceof PogoZombie) {
            PogoZombie pz = (PogoZombie) host;
            pz.setStats();
        }

        return expire;
    }
}
