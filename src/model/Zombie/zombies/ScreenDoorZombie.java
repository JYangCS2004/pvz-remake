package model.Zombie.zombies;

import model.StatusEffect.Effects.ShieldedEffect;
import model.StatusEffect.StatusEffect;
import model.Zombie.Zombie;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.ListIterator;

public class ScreenDoorZombie extends Zombie {
    static final double SPEED = -0.5;
    static final int DAMAGE = 2;
    static final int HEALTH = 100;
    static final int EAT_TIME = 30;
    static final int HEIGHT = 48;
    static final int WIDTH = 48;
    //private PriorityQueue<StatusEffect> se;

    public ScreenDoorZombie(int x, int y, GamePanel g){
        super(x, y, SPEED, DAMAGE, HEALTH, EAT_TIME, WIDTH, HEIGHT, g);
        effectManager.add(new ShieldedEffect(this, 100));
    }

    @Override
    public void decreaseHealth(Projectile p){
        if(effectManager.contains("SHIELD")){
            ListIterator<StatusEffect> it = effectManager.getEffects().listIterator();
            while(it.hasNext()){
                StatusEffect e = it.next();
                if(e.getTag().equals("SHIELD")){
                    ((ShieldedEffect) e).update(p.getDamage());
                }
            }
        }else{
            super.decreaseHealth(p);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.g.getImageLibrary().getImage("sdz"), x, y, null);
    }
}
