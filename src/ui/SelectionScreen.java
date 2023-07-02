package ui;

import model.ImageLibrary;

import java.awt.*;

public class SelectionScreen {
    private int width = 14;
    private int height = 10;

    GamePanel g;
    ImageLibrary ib;
    PlantInterface pi;

    CustomButton button;

    private String[][] tags = new String[height][width];

    public SelectionScreen(GamePanel g, ImageLibrary ib, PlantInterface pi) {
        this.g = g;
        this.ib = ib;
        this.pi = pi;
        button = new CustomButton(g);

        int i = 0;
        for (String s : ib.getTagToCard().keySet()) {
            int row = i / 9;
            tags[row][i - row * 9] = s;
            i++;
        }

        int tile = g.getTileSize();
        button.setBounds(4 * tile + tile / 2, 9 * tile + tile / 2, tile * 2, tile);
        g.add(button);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(139,69,19));
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
        pi.addSelected(ib.getCard(tags[y - 2][x - 1]));
    }
}
