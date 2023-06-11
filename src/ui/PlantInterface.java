package ui;

import model.Plant.Plant;
import model.Plant.plants.*;
import model.SpawnManager.RandSpawnManager.RandSpawners.SunSpawner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PlantInterface {

    private GamePanel gamePanel;

    // private String[] plants = new String[9];
    private Card[] plants = new Card[10];

    final Color interfaceColor = new Color(	0,128,0);

    public int selected = 0;

    public PlantInterface(GamePanel g){
        this.gamePanel = g;
        //temp code until plant selection system is implemented
        plants[0] = new Card("ps", 5, g);
        plants[1] = new Card("wn", 15, g);
        plants[2] = new Card("ch", 15, g);
        plants[3] = new Card("sf", 5, g);
        plants[4] = new Card("cp", 5, g);
        plants[5] = new Card("ja", 30, g);
        plants[6] = new Card("fs", 10, g);
        plants[7] = new Card("ips", 0, g);
        plants[8] = new Card("kp", 5, g);
        plants[9] = new Card("tw", 5, g);
    }

    public void draw(Graphics g, SunSpawner ss){
        g.setColor(interfaceColor);
        g.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getTileSize());
        g.setColor(Color.white);
        g.drawString(Integer.toString(ss.getSunCount()),
                gamePanel.getScreenWidth()- gamePanel.getTileSize(), gamePanel.getTileSize()/2);
        //so far using this bc only 2 plants
        for (int i = 0; i < 10; i++) {
            g.drawImage(gamePanel.getImageLibrary().getImage(plants[i].getTag()),
                    i*gamePanel.getTileSize() + gamePanel.getImageLibrary().getXFix(plants[i].getTag()),
                    gamePanel.getImageLibrary().getYFix(plants[i].getTag()), null);
            plants[i].draw(g, i);
            g.setColor(Color.white);
            g.drawRoundRect(i * gamePanel.getTileSize(), 0, gamePanel.getTileSize(), gamePanel.getTileSize(), 5, 5);
        }

        try {
            g.drawImage(ImageIO.read(new File("src/Graphics/Shovel2.png")).getScaledInstance(45, 45, Image.SCALE_SMOOTH),
                    13 * gamePanel.getTileSize(), 0, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g.setColor(Color.black);

        g.drawRoundRect(this.selected* gamePanel.getTileSize(),
                0, gamePanel.getTileSize(), gamePanel.getTileSize(), 10, 10);
        g.setColor(Color.white);

    }

    public Plant plantPicker(int x, int y, GamePanel g){

        if (selected == 13) {
            return null;
        }

        Card card = this.plants[this.selected];

        if (!card.isCanPlant()) {
            return null;
        }

        String plantType = card.getTag();
        switch (plantType) {
            case "tw":
                return plantChecker(new TorchWood(x, y, g), card);
            case "kp":
                return plantChecker(new KernelPult(x, y, g), card);
            case "ips":
                return plantChecker(new IcePeaShooter(x, y, g), card);
            case "ja":
                return plantChecker(new Jalapeno(x, y, g), card);
            case "cp":
                return plantChecker(new CabbagePult(x, y, g), card);
            case "sf":
                return plantChecker(new Sunflower(x, y, g), card);
            case "wn":
                return plantChecker(new Walnut(x, y, g), card);
            case "ch":
                return plantChecker(new Chomper(x, y, g), card);
            case "fs":
                return plantChecker(new FumeShroom(x, y, g), card);
            default:
                return plantChecker(new Peashooter(x, y, g), card);
        }
    }

    private Plant plantChecker(Plant p, Card c) {
        if (((SunSpawner) gamePanel.getSunSpawner()).getSunCount() >= p.getCost() && c.canPlant
        && !gamePanel.getPlantManager().containsSquare(p)) {
            c.canPlant = false;
            c.resetHeight();
            return p;
        }

        return null;
    }


}
