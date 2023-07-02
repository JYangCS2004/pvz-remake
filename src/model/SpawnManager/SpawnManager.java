package model.SpawnManager;

import model.Entity;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SpawnManager {
    protected GamePanel gamePanel;

    protected List<Entity> entities;

    public SpawnManager(GamePanel g) {
        this.gamePanel = g;
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

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
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
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public void drawEach(Graphics g) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(g);
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
