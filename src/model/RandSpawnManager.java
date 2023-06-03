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
            int row = e.getY() / gamePanel.getTileSize();
            if (!rowEntities.containsKey(row)) {
                Queue<Entity> eList = new PriorityQueue<>(new EntityComparator());
                eList.add(e);
                rowEntities.put(row, eList);
            } else {
                rowEntities.get(row).add(e);
            }

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
                if (rowEntities.get(i).size() != 0) {
                    Projectile p = (Projectile) rowEntities.get(i).peek();

                    if (p != null && p.checkCollision()) {
                        rowEntities.get(i).remove(p);
                    }
                }
            }
        }

        super.updateEach();
    }
}
