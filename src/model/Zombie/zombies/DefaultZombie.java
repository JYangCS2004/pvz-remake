package model.Zombie.zombies;

import model.Zombie.Zombie;
import ui.GamePanel;

public class DefaultZombie extends Zombie {

    static final double SPEED = -0.5;
    static final int DAMAGE = 1;
    static final int HEALTH = 100;
    static final int EAT_TIME = 30;
    static final int HEIGHT = 48;
    static final int WIDTH = 48;
    //private PriorityQueue<StatusEffect> se;

    public DefaultZombie(int x, int y, GamePanel g){
        super(x, y, SPEED, DAMAGE, HEALTH, EAT_TIME, WIDTH, HEIGHT, g);
    }

}
