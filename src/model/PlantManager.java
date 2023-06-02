package model;

import ui.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlantManager extends SpawnManager {
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
}
