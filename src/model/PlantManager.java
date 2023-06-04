package model;

import ui.GamePanel;

public class PlantManager extends SpawnManager {
    boolean[][] plantedSpots = new boolean[gamePanel.getScreenColSize()][gamePanel.getScreenRowSize()];
    public PlantManager(GamePanel g) {
        super(g);
        /* g.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int nearEdgeX = e.getPoint().x / g.getTileSize();
                int nearEdgeY = e.getPoint().y / g.getTileSize();

                if (!plantedSpots[nearEdgeX][nearEdgeY]) {
                    spawn(new Peashooter(g.getTileSize() * nearEdgeX, g.getTileSize() * nearEdgeY, g));
                    plantedSpots[nearEdgeX][nearEdgeY] = true;
                }
            }
        }); */
    }

    public boolean containsSquare(int row, int col) {
        return plantedSpots[row][col];
    }

    public void storeSquare(int row, int col) {
        plantedSpots[row][col] = true;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
