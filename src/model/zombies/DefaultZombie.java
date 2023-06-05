package model.zombies;

import model.Zombie;
import ui.GamePanel;

public class DefaultZombie extends Zombie {

    static final int SPEED = -1;
    static final int DAMAGE = 2;
    static final int HEALTH = 25;
    static final int EAT_TIME = 30;
    static final int HEIGHT = 16;
    static final int WIDTH = 16;
    //private PriorityQueue<StatusEffect> se;

    public DefaultZombie(int x, int y, GamePanel g){
        super(x, y, SPEED, DAMAGE, HEALTH, EAT_TIME, WIDTH, HEIGHT, g);
    }

    @Override
    public int getSpeed(){
        // return applyStatusEffect(SPEED);
        return SPEED;
    }
}
