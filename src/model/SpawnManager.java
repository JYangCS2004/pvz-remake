package model;

import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SpawnManager {
    protected GamePanel gamePanel;

    protected Map<Integer, List<Entity>> rowEntities;

    public SpawnManager(GamePanel g) {
        this.gamePanel = g;
        rowEntities = new HashMap<>();
    }

    // REQUIRES: x >= 0, y >= 0
    // MODIFIES: this
    // EFFECTS: constructs a new projectile
    public void spawn(Entity p) {
        int row = p.getY() / gamePanel.getTileSize();
        if (!rowEntities.containsKey(row)) {
            List<Entity> eList = new ArrayList<>();
            eList.add(p);
            rowEntities.put(row, eList);
        } else {
            rowEntities.get(row).add(p);
        }
    }

    public void removeByRow(Entity e, int row) {
        if (rowEntities.containsKey(row)) {
            rowEntities.get(row).remove(e);
        }
    }

    public List<Entity> getEntitiesByRow(int row) {
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
