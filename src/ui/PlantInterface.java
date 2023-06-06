package ui;

import model.plants.CabbagePult;
import model.plants.Chomper;
import model.plants.Peashooter;
import model.Plant;
import model.SunSpawner;
import model.plants.Walnut;

import java.awt.*;

public class PlantInterface {

    private GamePanel gamePanel;

    private String[] plants = new String[9];

    final Color interfaceColor = new Color(	0,128,0);

    public int selected = 0;

    public PlantInterface(GamePanel g){
        this.gamePanel = g;
        //temp code until plant selection system is implemented
        plants[0] = "ps";
        plants[1] = "wn";
        plants[2] = "ch";
        plants[3] = "cp";
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
        for (int i = 0; i < 4; i++) {
            g.drawImage(gamePanel.getImageLibrary().getImage(plants[i]),
                    i*gamePanel.getTileSize() + gamePanel.getImageLibrary().getXFix(plants[i]),
                    gamePanel.getImageLibrary().getYFix(plants[i]), null);
        }

    }

    public Plant plantPicker(int x, int y, GamePanel g){
        String plantType = this.plants[this.selected];
        switch (plantType) {
            case "wn":
                return new Walnut(x, y, g);
            case "ch":
                return new Chomper(x, y, g);
            case "cp":
                return new CabbagePult(x, y, g);
            default:
                return new Peashooter(x, y, g);
        }
    }


}
