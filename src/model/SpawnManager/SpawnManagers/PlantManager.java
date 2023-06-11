package model.SpawnManager.SpawnManagers;

import model.Entity;
import model.SpawnManager.SpawnManager;
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
                    spawn(new Walnut(g.getTileSize() * nearEdgeX, g.getTileSize() * nearEdgeY, g));
                    plantedSpots[nearEdgeX][nearEdgeY] = true;
                }
            }
        }); */
    }

    public void remove(Entity e) {
        entities.remove(e);
        freeSquare(e.getX() / gamePanel.getTileSize(), e.getY() / gamePanel.getTileSize());
    }

    public boolean containsSquare(Entity e) {
        return plantedSpots[e.getX() / gamePanel.getTileSize()][e.getY() / gamePanel.getTileSize()];
    }

    public void spawn(Entity e) {
        super.spawn(e);
        plantedSpots[e.getX() / gamePanel.getTileSize()][e.getY() / gamePanel.getTileSize()] = true;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    private void freeSquare(int row, int col) {
        plantedSpots[row][col] = false;
    }
}
