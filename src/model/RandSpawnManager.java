package model;

import ui.GamePanel;

import java.util.*;

public class RandSpawnManager extends SpawnManager {
    public enum Type {BULLET, ENEMY}
    private final int spawnTime;
    private int counter;
    private final Type type;

    private static class EntityComparator implements Comparator<Entity> {

        @Override
        public int compare(Entity o1, Entity o2) {
            return Integer.compare(o2.x, o1.x);
        }
    }

    public RandSpawnManager(int spawnTime, GamePanel g, Type t) {
        super(g);
        this.spawnTime = spawnTime;
        counter = spawnTime;
        this.type = t;
    }

    @Override
    public void spawn(Entity e) {
        counter--;
        if (counter == 0) {
            super.spawn(e);
            counter = spawnTime;
        }
    }

    @Override
    public void updateEach() {
        if (type == Type.ENEMY) {
            Random rand = new Random();
            spawn(new Zombie(gamePanel.getScreenWidth(), 48 * rand.nextInt(gamePanel.getScreenRowSize()), gamePanel));

        } else if (type == Type.BULLET){

            for (int i : rowEntities.keySet()) {
                Iterator<Entity> it = rowEntities.get(i).iterator();

                while (it.hasNext()) {
                    Projectile p = (Projectile) it.next();
                    if (p.checkCollision()) {
                        it.remove();
                    }
                }
            }
        }

        super.updateEach();
    }
}
