package model;

import ui.GamePanel;

import java.util.Random;

public class RandSpawnManager extends SpawnManager {
    public enum Type {BULLET, ENEMY}
    private final int spawnTime;
    private int counter;
    private final Type type;

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
            spawn(new Zombie(gamePanel.getScreenWidth(), 48 * rand.nextInt(gamePanel.getScreenRowSize())));
        } else if (type == Type.BULLET){
            for (int i : rowEntities.keySet()) {
                if (rowEntities.get(i).size() != 0) {
                    Projectile p = (Projectile) rowEntities.get(i).get(0);

                    if (p.checkCollision()) {
                        rowEntities.get(i).remove(p);
                    }
                }
            }
        }

        super.updateEach();
    }
}
