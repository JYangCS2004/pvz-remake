package model;

import ui.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class PlantManager extends SpawnManager {
    private static class EntityComparator implements Comparator<Entity> {

        @Override
        public int compare(Entity o1, Entity o2) {
            return Integer.compare(o2.x, o1.x);
        }
    }

    int[][] plantedSpots = new int[gamePanel.getScreenRowSize()][gamePanel.getScreenColSize()];
    public PlantManager(GamePanel g) {
        super(g);
        g.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int nearEdgeX = e.getPoint().x / g.getTileSize();
                int nearEdgeY = e.getPoint().y / g.getTileSize();
                spawn(new Plant(g.getTileSize() * nearEdgeX, g.getTileSize() * nearEdgeY, g));
            }
        });
    }


    public void spawn(Entity e) {
        int row = e.getY() / gamePanel.getTileSize();
        if (!rowEntities.containsKey(row)) {
            Queue<Entity> eList = new PriorityQueue<>(new EntityComparator());
            eList.add(e);
            rowEntities.put(row, eList);
        } else {
            rowEntities.get(row).add(e);
        }
    }
}
