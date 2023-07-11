package model.Plant.plants;

import model.ImageLibrary;
import model.MouseHandler;
import model.Plant.Plant;
import model.projectiles.Bullet.CornCob;
import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CobCannon extends Plant {
    private int spawnTime = 1000;
    private int counter;

    private static final String TAG = "cc";
    private static final int HEALTH = 80;
    private static final int COST = 500;
    private boolean displaced;
    private boolean movedAlready;
    CobEngage button;

    public CobCannon(int x, int y, GamePanel g) {
        super(x, y, HEALTH, TAG, g, COST);
        button = new CobEngage(this.x, this.y);

        width = g.getTileSize() * 2;
        counter = 0;
    }

    @Override
    public void update() {
        if (displaced) {
            if (!movedAlready) {
                x -= g.getTileSize();
                button.setBounds(x, y, width, height);
            }
            movedAlready = true;
        }


        counter--;

    }

    public void launch(int x, int y) {
        CornCob fired = new CornCob(this.x, this.y, this, g);
        g.getShooterManager().spawn(fired);
        fired.setDestination(x, y);

        counter = spawnTime;
    }

    @Override
    public void draw(Graphics g) {
        Image image = ImageLibrary.getImage("src/Graphics/cobcannon.png", this.g.getTileSize() * 538 / 349, this.g.getTileSize());
        g.drawImage(image, x, y, null);
        if (button.over) {
            g.setColor(Color.RED);
            g.drawRoundRect(x - 2, y - 2, width + 2, height + 2, 10, 10);
        }

        g.setColor(Color.red);
        if (counter > 0) {
            g.drawString(Integer.toString(counter), x + this.g.getTileSize() - 10, y + this.g.getTileSize() / 3 - 10);
        }

        g.setColor(Color.white);
        g.drawString(Integer.toString(health), x + this.g.getTileSize() - 10, y + this.g.getTileSize() / 3);
    }

    @Override
    public void setTimer() {
        counter = spawnTime;
    }


    @Override
    public String getPlantCondition() {
        return "kp";
    }

    public void displace() {
        displaced = true;
    }

    public void removeButton() {
        g.remove(button);
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
                    if (counter <= 0) {
                        over = true;
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    over = false;
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (counter <= 0) {
                        g.mouseState = 1;
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
                ((MouseHandler) ml).engageCobCannon(CobCannon.this);
            }
        }
    }
}
