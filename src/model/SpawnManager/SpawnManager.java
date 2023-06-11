package model.SpawnManager;

import model.Entity;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SpawnManager {
    protected GamePanel gamePanel;

    protected Map<Integer, List<Entity>> rowEntities;

    protected List<Entity> entities;

    public SpawnManager(GamePanel g) {
        this.gamePanel = g;
        rowEntities = new HashMap<>();
        entities = new ArrayList<>();
    }

    // REQUIRES: x >= 0, y >= 0
    // MODIFIES: this
    // EFFECTS: constructs a new projectile
    public void spawn(Entity e) {
        /* int row = e.getY() / gamePanel.getTileSize();
        if (!rowEntities.containsKey(row)) {
            List<Entity> eList = new ArrayList<>();
            eList.add(e);
            rowEntities.put(row, eList);
        } else {
            rowEntities.get(row).add(e);
        } */

        entities.add(e);
    }


    public List<Entity> getEntitiesByRow(int row) {
        List<Entity> rowEntities = new ArrayList<>();

        for (Entity e : entities) {
            if (e.getRow() == row) {
                rowEntities.add(e);
            }
        }

        return rowEntities;
    }

    // EFFECTS: updates the position of each entity
    /*public void updateEach() {
        for (int i : rowEntities.keySet()) {
            rowEntities.get(i).forEach((Entity::update));
        }
    } */

    public void updateEach() {
        for (Entity e : entities) {
            e.update();
        }
    }

    public void drawEach(Graphics g) {
        for (Entity e : entities) {
            e.draw(g);
        }
    }

    public List<Entity> getEntities(){
        return entities;
    }

    /*
    public void drawEach2(Graphics g){
        for (Entity e : allEntities) {
            e.draw(g);
        }
    } */
}
