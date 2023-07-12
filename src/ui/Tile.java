package ui;

import model.Plant.Plant;

public class Tile {

    protected String tag;

    private Plant plant;
    public Tile(){
        tag = "na";
    }

    public void resetTile(){
        plant = null;
    }
    public String getTag(){
        if (plant == null) {
            return "na";
        }

        return plant.getTag();
    }

    public void plantTile(Plant p){
        plant = p;
        tag = p.getTag();
    }

    public Plant getPlant() {
        return plant;
    }
}
