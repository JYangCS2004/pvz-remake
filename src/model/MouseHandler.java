package model;

import model.Plant.Plant;
import model.SpawnManager.SpawnManagers.PlantManager;
import model.SpawnManager.RandSpawnManager.RandSpawners.SunSpawner;
import ui.PlantInterface;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.util.Iterator;
import java.util.List;

public class MouseHandler extends MouseAdapter implements MouseMotionListener {
    PlantManager pm;
    SunSpawner ss;

    PlantInterface pi;


    public MouseHandler(PlantManager pm, SunSpawner ss, PlantInterface pi) {
        this.pm = pm;
        this.ss = ss;
        this.pi = pi;
        pm.getGamePanel().addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouse = e.getPoint();
        int x = mouse.x;
        int y = mouse.y;
        boolean foundSun = false;

        List<Entity> sunList = ss.getSuns();
        Iterator<Entity> it = sunList.iterator();

        while (it.hasNext()) {
            Sun s = (Sun) it.next();
            if (new Area(s.getBounds()).contains(x, y)) {
                foundSun = true;
                ss.incrementSun();
                it.remove();
                break;
            }
        }

        if (!foundSun) {
            int tile = pm.getGamePanel().getTileSize();
            int nearEdgeX = x / tile;
            int nearEdgeY = y / tile;

            //check if mouse is at shop interface
            if (nearEdgeY == 0) {
                pi.selected = nearEdgeX;
            }
            //spawn plant if not at interface
            else {
                Plant p = pi.plantPicker(nearEdgeX * tile, nearEdgeY * tile, pm.getGamePanel());
                if (!pm.containsSquare(nearEdgeX, nearEdgeY) && ss.getSunCount() >= p.getCost()) {
                    pm.spawn(p);
                    ss.deductSum(p.getCost());
                    pm.storeSquare(nearEdgeX, nearEdgeY);
                }
            }
        }
    }
    @Override
    public void mouseMoved(MouseEvent e){
        Point mouse = e.getPoint();
        int x = mouse.x;
        int y = mouse.y;

        List<Entity> sunList = ss.getSuns();
        Iterator<Entity> it = sunList.iterator();

        while (it.hasNext()) {
            Sun s = (Sun) it.next();
            if (new Area(s.getBounds()).contains(x, y)) {
                ss.incrementSun();
                it.remove();
                return;
            }
        }
    }
}
