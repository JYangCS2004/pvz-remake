package ui;

import model.Plant.Plant;

public class Tile {

    protected String tag;
    public Tile(){
        tag = "na";
    }

    public void resetTile(){
        tag = "na";
    }
    public String getTag(){
        return tag;
    }
    public void plantTile(Plant p){
        tag = p.getTag();
    }
}
