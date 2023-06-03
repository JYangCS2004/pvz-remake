package model;

import ui.GamePanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public abstract class SpawnManager {
    protected GamePanel gamePanel;

    protected Map<Integer, Queue<Entity>> rowEntities;

    public SpawnManager(GamePanel g) {
        this.gamePanel = g;
        rowEntities = new HashMap<>();
    }

    // REQUIRES: x >= 0, y >= 0
    // MODIFIES: this
    // EFFECTS: constructs a new projectile
    public void spawn(Entity p) {

    }

    public Queue<Entity> getEntitiesByRow(int row) {
        return rowEntities.get(row);
    }

    // EFFECTS: updates the position of each entity
    public void updateEach() {
        for (int i : rowEntities.keySet()) {
            rowEntities.get(i).forEach((entity -> entity.update()));
        }
    }

    public void drawEach(Graphics g) {
        for (int i : rowEntities.keySet()) {
            rowEntities.get(i).forEach((entity -> entity.draw(g)));
        }
    }
}
