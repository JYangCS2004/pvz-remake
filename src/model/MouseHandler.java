package model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.Iterator;
import java.util.List;

public class MouseHandler extends MouseAdapter {
    PlantManager pm;
    SunSpawner ss;
    public MouseHandler(PlantManager pm, SunSpawner ss) {
        this.pm = pm;
        this.ss = ss;
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

            pm.spawn(new Peashooter(nearEdgeX * tile, nearEdgeY * tile, pm.getGamePanel()));
        }
    }
}
