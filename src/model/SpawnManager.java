package model;

import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public abstract class SpawnManager {
    protected GamePanel gamePanel;

    protected Map<Integer, List<Entity>> rowEntities;

    protected List<Entity> allEntities;

    public SpawnManager(GamePanel g) {
        this.gamePanel = g;
        rowEntities = new HashMap<>();
        allEntities = new ArrayList<>();
    }

    // REQUIRES: x >= 0, y >= 0
    // MODIFIES: this
    // EFFECTS: constructs a new projectile
    public void spawn(Entity e) {
        int row = e.getY() / gamePanel.getTileSize();
        if (!rowEntities.containsKey(row)) {
            List<Entity> eList = new ArrayList<>();
            eList.add(e);
            rowEntities.put(row, eList);
        } else {
            rowEntities.get(row).add(e);
        }
        allEntities.add(e);
    }

    public List<Entity> getEntitiesByRow(int row) {
        return rowEntities.get(row);
    }

    // EFFECTS: updates the position of each entity
    public void updateEach() {
        for (int i : rowEntities.keySet()) {
            rowEntities.get(i).forEach((Entity::update));
        }
    }

    public void drawEach(Graphics g) {
        for (int i : rowEntities.keySet()) {
            rowEntities.get(i).forEach((entity -> entity.draw(g)));
        }
    }
    public void drawEach2(Graphics g){
        for (Entity e : allEntities) {
            e.draw(g);
        }
    }
}
