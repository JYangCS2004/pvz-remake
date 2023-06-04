package model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    PlantManager pm;
    public MouseHandler(PlantManager pm) {
        this.pm = pm;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
