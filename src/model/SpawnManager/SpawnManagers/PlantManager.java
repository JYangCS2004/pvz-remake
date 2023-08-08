package model.SpawnManager.SpawnManagers;

import model.Entity;
import model.Plant.Plant;
import model.Plant.plants.CobCannon;
import model.Plant.plants.PlantZombie;
import model.Plant.plants.Pumpkin;
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
        if (e instanceof PlantZombie) {
            directRemove(e);
            return;
        }

        int row = e.getX() / gamePanel.getTileSize();
        int col = e.getY() / gamePanel.getTileSize();

        if (e instanceof CobCannon) {
            ((CobCannon) e).removeButton();
        }

        if (((Plant) e).getTag().equals("cc")) {
            System.out.println(plantedSpots[row + 1][col].getTag());
            if (("cc").equals(plantedSpots[row + 1][col].getTag())) {
                plantedSpots[row + 1][col].resetTile();
            } else {
                plantedSpots[row - 1][col].resetTile();
            }
        }

        entities.remove(e);
        plantedSpots[e.getX() / gamePanel.getTileSize()][e.getY() / gamePanel.getTileSize()].resetTile();
        if (((Plant) e).hasShield() && !((Plant) e).getTag().equals("ds")) {
            spawn(((Plant) e).getShield());
        }
    }

    public boolean canPlant(Plant e) {
        int row = e.getX() / gamePanel.getTileSize();
        int col = e.getY() / gamePanel.getTileSize();

        if (e.getTag().equals("cc")) {
            if (row + 1 < gamePanel.getScreenColSize() && ("kp").equals(plantedSpots[row + 1][col].getTag())) {

                return CobCannon.checkPlantable(plantedSpots[row + 1][col].getPlant(), plantedSpots[row][col].getPlant());

            } else if (row - 1 >= 0 && ("kp").equals(plantedSpots[row - 1][col].getTag())) {
                boolean spot = CobCannon.checkPlantable(plantedSpots[row - 1][col].getPlant(), plantedSpots[row][col].getPlant());
                System.out.println(spot);
                if (spot) {
                    ((CobCannon) e).displace();
                    return true;
                } else {
                    return false;
                }
            } else {
                ((CobCannon) e).removeButton();
                return false;
            }
        }

        if (e.getTag().equals("pk")) {
            Plant temp = plantedSpots[row][col].getPlant();

            if (temp != null) {
                return !temp.hasShield() && !temp.getTag().equals("cc");
            }

            return true;
        }

        if (("pk").equals(plantedSpots[row][col].getTag())) {
            return true;
        }

        return e.getPlantCondition().equals(plantedSpots[row][col].getTag());
    }

    @Override
    public void spawn(Entity e) {
        int row = e.getX() / gamePanel.getTileSize();
        int col = e.getY() / gamePanel.getTileSize();

        if (((Plant) e).getTag().equals("pk")) {
            if (((Plant)e).getPlantCondition().equals(plantedSpots[row][col].getTag())) {
                plantedSpots[row][col].plantTile((Plant) e);
                super.spawn(e);
                return;
            }

            Pumpkin.stack(plantedSpots[row][col].getPlant(), (Pumpkin) e);
            return;
        }

        if (((Plant) e).getTag().equals("cc")) {
            if (row + 1 < gamePanel.getScreenColSize() && ("kp").equals(plantedSpots[row + 1][col].getTag())) {
                refill(row + 1, col, e);
                plantedSpots[row + 1][col].plantTile((Plant)e);
            } else {
                refill(row - 1, col, e);
                plantedSpots[row - 1][col].plantTile((Plant)e);
            }
        }

        refill(row, col, e);
        plantedSpots[row][col].plantTile((Plant)e);
        super.spawn(e);
    }

    public void refill(int row, int col, Entity p){
        Plant temp = plantedSpots[row][col].getPlant();

        if (temp != null) {
            if (temp instanceof Pumpkin) {
                System.out.println("stack");
                ((Plant) p).transferPumpkinStatus((Pumpkin) temp);
            }

            if (((Plant) p).isUpgradable()) {
                ((Plant) p).transfer(temp);
            }

            entities.remove(temp);
        /*for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            if((e.getX() / gamePanel.getTileSize()) == row
            && (e.getY() / gamePanel.getTileSize()) == col){
                if (e instanceof Pumpkin) {
                    ((Plant) p).transferPumpkinStatus((Pumpkin) e);
                }

                if (((Plant) p).isUpgradable()) {
                    ((Plant) p).transfer((Plant) e);
                }

                entities.remove(e);
                break;
            } */
        }
    }

    private void stackPumpkin(int row, int col, Pumpkin p) {
        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            if((e.getX() / gamePanel.getTileSize()) == row
                    && (e.getY() / gamePanel.getTileSize()) == col){

                ((Plant) e).transferPumpkinStatus(p);
                break;
            }
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void spawnWithoutRegister(Entity e) {
        super.spawn(e);
    }

    public void directRemove(Entity e) {
        entities.remove(e);
    }
}
