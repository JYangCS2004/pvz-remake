package ui;

import model.ImageLibrary;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SelectionScreen {
    private int width = 14;
    private int height = 10;

    GamePanel g;
    ImageLibrary ib;
    PlantInterface pi;

    private String[][] tags = new String[height][width];

    public SelectionScreen(GamePanel g, ImageLibrary ib, PlantInterface pi) {
        this.g = g;
        this.ib = ib;
        this.pi = pi;

        int i = 0;
        for (String s : ib.getTagToCard().keySet()) {
            int row = i / 9;
            tags[row][i - row * 9] = s;
            i++;
        }

        g.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    g.startGame();
                };
            }
        });
    }

    public void draw(Graphics g) {
        int tile = this.g.getTileSize();
        g.fillRoundRect(tile / 2, 2 * tile, 10 * tile, 9 * tile, 15, 15);
        g.setColor(new Color(222, 184, 135));
        ((Graphics2D) g).setStroke(new BasicStroke(10));
        g.drawRoundRect(tile / 2 - 5, 2 * tile - 5, 10 * tile + 10, 9 * tile + 10, 20, 20);

        int i = 0;
        for (String s : ib.getTagToCard().keySet()) {
            int row = i / 9;
            int column = i - row * 9;

            g.setColor(new Color(255, 255, 255));
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawRoundRect((column + 1) * tile, (row + 2) * tile, tile, tile, 15, 15);
            g.drawImage(ib.getImage(s),
                    ib.getXFix(s) + (column + 1) * tile,
                    ib.getYFix(s) + (row + 2) * tile, null);

            if (pi.hasCard(s)) {
                g.setColor(new Color(0, 0, 0, 125));
                g.fillRoundRect((column + 1) * tile, (row + 2) * tile, tile, tile, 15, 15);
            }

            i++;
        }

    }

    public void assignCard(int x, int y) {
        System.out.println("Row " + (y - 2) + " Column " + (x - 1) + ":" + tags[y - 2][x - 1]);
        pi.addSelected(ib.getCard(tags[y - 2][x - 1]));
    }
}
