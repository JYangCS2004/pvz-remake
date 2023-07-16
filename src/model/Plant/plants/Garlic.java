package model.Plant.plants;

import model.Plant.Plant;
import model.Zombie.Zombie;
import ui.GamePanel;

public class Garlic extends Plant {
    private static int HEALTH = 30;
    private static final String TAG = "gl";
    private static final int COST = 50;
    private Zombie lastCollided;

    public Garlic(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
    }

    public static boolean shouldSwitchLanes(Plant other) {
        if (other instanceof Garlic) {
            return !other.hasShield();
        } else {
            return false;
        }
    }

    public boolean isDifferent(Zombie zombie) {
        boolean different = lastCollided != zombie;
        if (different) {
            lastCollided = zombie;
        }

        return different;
    }
}
