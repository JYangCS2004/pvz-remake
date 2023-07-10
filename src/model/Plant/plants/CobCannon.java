package model.Plant.plants;

import model.MouseHandler;
import model.Plant.Plant;
import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CobCannon extends Plant {
    private int spawnTime = 1000;
    private int counter = 0;

    private static final String TAG = "cc";
    private static final int HEALTH = 80;
    private static final int COST = 500;

    CobEngage button;

    public CobCannon(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        button = new CobEngage(x, y);

        width = g.getTileSize() * 2;
    }

    @Override
    public void update() {

    }

    public void launchCob() {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.fillRoundRect(x, y, width, height, 10, 10);
        if (button.over) {
            g.setColor(Color.RED);
            g.drawRoundRect(x - 2, y - 2, width + 2, height + 2, 10, 10);
        }

        g.setColor(Color.white);
    }

    @Override
    public void setTimer() {
        counter = spawnTime;
    }

    private class CobEngage extends JButton {
        boolean over;
        CobEngage(int x, int y) {
            int baseSize = g.getTileSize();
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBounds(x, y, baseSize * 2, baseSize);
            g.add(this);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    over = true;
                    System.out.println("over!");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    over = false;
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (counter == 0) {
                        notifyMouseListener();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }

        private void notifyMouseListener() {
            for (MouseListener ml : g.getMouseListeners()) {
                ((MouseHandler) ml).engageCobCannon();
            }
        }
    }
}
