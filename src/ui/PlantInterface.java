package ui;

import model.plants.Peashooter;
import model.Plant;
import model.SunSpawner;
import model.plants.Walnut;

import java.awt.*;

public class PlantInterface {

    protected GamePanel gamePanel;

    protected String[] plants = new String[9];

    final Color interfaceColor = new Color(	0,128,0);

    public int selected = 0;

    public PlantInterface(GamePanel g){
        this.gamePanel = g;
        //temp code until plant selection system is implemented
        plants[0] = "ps";
        plants[1] = "wn";
    }

    public void draw(Graphics g, SunSpawner ss){
        g.setColor(interfaceColor);
        g.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getTileSize());
        g.setColor(Color.white);
        g.drawString(Integer.toString(ss.getSunCount()),
                gamePanel.getScreenWidth()- gamePanel.getTileSize(), gamePanel.getTileSize()/2);
        g.setColor(Color.black);
        g.drawRoundRect(this.selected* gamePanel.getTileSize(),
                0, gamePanel.getTileSize(), gamePanel.getTileSize(), 10, 10);
        //so far using this bc only 2 plants
        for(int i = 0; i < 2; i++){
            g.drawImage(gamePanel.getImageLibrary().getImage(plants[i]),
                    i*gamePanel.getTileSize() + gamePanel.getImageLibrary().getXFix(plants[i]),
                    gamePanel.getImageLibrary().getYFix(plants[i]), null);
        }

    }

    public Plant plantPicker(int x, int y, GamePanel g){
        //add things later
        String plantType = this.plants[this.selected];
        if(plantType.equals("ps")){
            return new Peashooter(x, y, g);
        }
        else if(plantType.equals("wn")){
            return new Walnut(x, y, g);
        }
        // for now since we only have two plant types in total
        else{
            return new Peashooter(x, y, g);
        }
    }


}
