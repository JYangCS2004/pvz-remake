package model;

import model.Plant.Plant;
import model.SpawnManager.SpawnManagers.PlantManager;
import model.SpawnManager.RandSpawnManager.RandSpawners.SunSpawner;
import ui.GamePanel;
import ui.PlantInterface;
import ui.SelectionScreen;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.util.Iterator;
import java.util.List;

public class MouseHandler extends MouseAdapter implements MouseMotionListener {
    private boolean hasShovel;
    PlantManager pm;
    SunSpawner ss;

    PlantInterface pi;
    SelectionScreen screen;


    public MouseHandler(PlantManager pm, SunSpawner ss, PlantInterface pi, SelectionScreen s) {
        this.pm = pm;
        this.ss = ss;
        this.pi = pi;
        this.screen = s;
        pm.getGamePanel().addMouseMotionListener(this);
        hasShovel = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouse = e.getPoint();
        int x = mouse.x;
        int y = mouse.y;

        int tile = pm.getGamePanel().getTileSize();
        int nearEdgeX = x / tile;
        int nearEdgeY = y / tile;

        if (pm.getGamePanel().getGameState() == GamePanel.SELECTION_STATE) {
            if (nearEdgeX >= 1 && nearEdgeY >= 2 && nearEdgeY <= 4 && nearEdgeX <= 9) {
                screen.assignCard(nearEdgeX, nearEdgeY);
            }

            if (nearEdgeY == 0) {
                pi.selected = nearEdgeX;
                pi.removeSelected();
            }
        } else {
            if (hasShovel) {
                Plant p = null;
                for (Entity entity : pm.getEntities()) {
                    if (nearEdgeX * tile == entity.getX() && nearEdgeY * tile == entity.getY()) {
                        p = (Plant) entity;
                    }
                }

                if (p != null) {
                    ss.incrementSun(p.getCost() / 2);
                    pm.remove(p);
                }

                // hasShovel = false;
            }

            //check if mouse is at shop interface
            if (nearEdgeY == 0) {
                hasShovel = (nearEdgeX == 13);
                pi.selected = nearEdgeX;
            }

            //spawn plant if not at interface
            else {
                Plant p = pi.plantPicker(nearEdgeX * tile, nearEdgeY * tile, pm.getGamePanel());

                if (p != null) {
                    pm.spawn(p);
                    ss.deductSum(p.getCost());
                }
            }
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        Point mouse = e.getPoint();
        int x = mouse.x;
        int y = mouse.y;

        List<Entity> sunList = ss.getSuns();
        Iterator<Entity> it = sunList.iterator();

        while (it.hasNext()) {
            Sun s = (Sun) it.next();
            if (new Area(s.getBounds()).contains(x, y)) {
                ss.incrementSun(SunSpawner.DEFAULT);
                it.remove();
                return;
            }
        }
    }
}
