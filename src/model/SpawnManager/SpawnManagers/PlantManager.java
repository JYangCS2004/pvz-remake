package model.SpawnManager.SpawnManagers;

import model.Entity;
import model.Plant.Plant;
import model.Plant.plants.CobCannon;
import model.SpawnManager.SpawnManager;
import ui.GamePanel;
import ui.Tile;

public class PlantManager extends SpawnManager {
    Tile[][] plantedSpots = new Tile[gamePanel.getScreenColSize()][gamePanel.getScreenRowSize()];
    public PlantManager(GamePanel g) {
        super(g);
        for(int i = 0; i < gamePanel.getScreenColSize(); i++){
            for(int u = 0; u < gamePanel.getScreenRowSize(); u++){
                plantedSpots[i][u] = new Tile();
            }
        }
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
        plantedSpots[e.getX() / gamePanel.getTileSize()][e.getY() / gamePanel.getTileSize()].resetTile();
    }

    public boolean canPlant(Plant e) {
        int row = e.getX() / gamePanel.getTileSize();
        int col = e.getY() / gamePanel.getTileSize();

        if (e.getTag().equals("cc")) {
            if (row + 1 < gamePanel.getScreenColSize() && ("kp").equals(plantedSpots[row + 1][col].getTag())) {

                return ("kp").equals(plantedSpots[row][col].getTag());
            } else if (row - 1 >= 0 && ("kp").equals(plantedSpots[row - 1][col].getTag())) {
                boolean spot = ("kp").equals(plantedSpots[row][col].getTag());
                if (spot) {
                    ((CobCannon) e).displace();
                    return true;
                }
            } else {
                ((CobCannon) e).removeButton();
                return false;
            }
        }

        return e.getPlantCondition().equals(plantedSpots[row][col].getTag());
    }

    public void spawn(Entity e) {
        int row = e.getX() / gamePanel.getTileSize();
        int col = e.getY() / gamePanel.getTileSize();

        //System.out.println(plantedSpots[row + 1][col].getTag());
        //System.out.println(plantedSpots[row - 1][col].getTag());

        if (((Plant) e).getTag().equals("cc")) {
            if (row + 1 < gamePanel.getScreenColSize() && ("kp").equals(plantedSpots[row + 1][col].getTag())) {
                removeByRowCol(row + 1, col);
                plantedSpots[row + 1][col].plantTile((Plant)e);
            } else {
                removeByRowCol(row - 1, col);
                plantedSpots[row - 1][col].plantTile((Plant)e);
            }
        }

        removeByRowCol(row, col);
        plantedSpots[row][col].plantTile((Plant)e);
        super.spawn(e);
    }

    public void removeByRowCol(int row, int col){
        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            if((e.getX() / gamePanel.getTileSize()) == row
            && (e.getY() / gamePanel.getTileSize()) == col){
                entities.remove(e);
                break;
            }
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
