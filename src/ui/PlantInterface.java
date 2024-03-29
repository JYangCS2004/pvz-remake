package ui;

import model.Plant.Plant;
import model.Plant.plants.*;
import model.SpawnManager.RandSpawnManager.RandSpawners.SunSpawner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlantInterface {
    public static final int MAX_CARDS = 9;
    private GamePanel gamePanel;

    // private String[] plants = new String[9];
    // private Card[] plants = new Card[11];
    private List<Card> plants;

    final Color interfaceColor = new Color(	0,128,0);

    public int selected = 0;

    public PlantInterface(GamePanel g){
        this.gamePanel = g;
        plants = new ArrayList<>();
    //temp code until plant selection system is implemented
        /* plants[0] = new Card("ps", 5, g);
        plants[1] = new Card("wn", 15, g);
        plants[2] = new Card("ch", 15, g);
        plants[3] = new Card("sf", 5, g);
        plants[4] = new Card("cp", 5, g);
        plants[5] = new Card("ja", 30, g);
        plants[6] = new Card("fs", 10, g);
        plants[7] = new Card("ips", 10, g);
        plants[8] = new Card("kp", 5, g);
        plants[9] = new Card("tw", 5, g);
        plants[10] = new Card("pm", 50, g);
         */
    }

    public void draw(Graphics g, SunSpawner ss){
        g.setColor(interfaceColor);
        g.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getTileSize());
        g.setColor(Color.white);
        g.drawString(Integer.toString(ss.getSunCount()),
                gamePanel.getScreenWidth()- gamePanel.getTileSize(), gamePanel.getTileSize()/2);
        //so far using this bc only 2 plants
        for (int i = 0; i < plants.size(); i++) {
            String tag = plants.get(i).getTag();

            g.drawImage(gamePanel.getImageLibrary().getImage(tag),
                    i*gamePanel.getTileSize() + gamePanel.getImageLibrary().getXFix(tag),
                    gamePanel.getImageLibrary().getYFix(tag), null);
            plants.get(i).draw(g, i);

            g.setColor(new Color(0, 0, 0, 124));
            // g.fillRoundRect(i * gamePanel.getTileSize(), 3 * 48 / 4, 48, 48 / 4, 5, 5);
            g.setColor(Color.white);
            //g.drawString("10", i * gamePanel.getTileSize() + 24, 3 * 48 / 4 + 5);


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

        Card card = this.plants.get(this.selected);

        if (!card.isCanPlant()) {
            return null;
        }

        String plantType = card.getTag();
        switch (plantType) {
            case "sq":
                return plantChecker(new Squash(x, y, g), card);
            case "wmp":
                return plantChecker(new WinterMelonPult(x, y, g), card);
            case "mp":
                return plantChecker(new MelonPult(x, y, g), card);
            case "gl":
                return plantChecker(new Garlic(x,y,g), card);
            case "stf":
                return plantChecker(new Starfruit(x,y,g), card);
            case "hs":
                return plantChecker(new HypnoShroom(x,y,g), card);
            case "ds":
                return plantChecker(new DoomShroom(x,y,g), card);
            case "sh":
                return plantChecker(new SunShroom(x,y,g), card);
            case "psh":
                return plantChecker(new PuffShroom(x,y,g), card);
            case "ss":
                return plantChecker(new ScaredyShroom(x,y,g), card);
            case "gs":
                return plantChecker(new GloomShroom(x,y,g), card);
            case "is":
                return plantChecker(new IceShroom(x, y, g), card);
            case "cb":
                return plantChecker(new CherryBomb(x,y,g), card);
            case "gp":
                return plantChecker(new GatlingPea(x, y, g), card);
            case "rp":
                return plantChecker(new Repeater(x, y, g), card);
            case "pm":
                return plantChecker(new PotatoMine(x, y, g), card);
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
            case "ct":
                return plantChecker(new Cattail(x, y, g), card);
            case "cc":
                return plantChecker(new CobCannon(x, y, g), card);
            case "pk":
                return plantChecker(new Pumpkin(x, y, g), card);
            default:
                return plantChecker(new Peashooter(x, y, g), card);
        }
    }

    private Plant plantChecker(Plant p, Card c) {
        if (((SunSpawner) gamePanel.getSunSpawner()).getSunCount() >= p.getCost() && c.canPlant &&
        gamePanel.getPlantManager().canPlant(p)) {
            c.canPlant = false;
            c.resetHeight();
            return p;
        }

        if (p instanceof CobCannon) {
            ((CobCannon) p).removeButton();
        }

        return null;
    }


    public void addSelected(Card card) {
        if (!plants.contains(card) && plants.size() < MAX_CARDS) {
            plants.add(card);
        }
    }

    public void removeSelected() {
        try {
            plants.remove(selected);
        } catch (RuntimeException e) {
            // do nothing
        }
    }

    public boolean hasCard(String s) {
        for (Card c : plants) {
            if (s.equals(c.getTag())) {
                return true;
            }
        }

        return false;
    }

    public boolean enoughCards() {
        return plants.size() == MAX_CARDS;
    }
}
