package model;

import ui.Card;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLibrary {

    protected Map<String, Image> imageList= new HashMap<>();
    protected Map<String, Integer> xFix= new HashMap<>();
    protected Map<String, Integer> yFix= new HashMap<>();
    protected Map<String, Integer> textXFix = new HashMap<>();
    protected Map<String, Integer> textYFix = new HashMap<>();
    private Map<String, Card> tagToCard = new HashMap<>();

    //some static images
    public static Image cabbage = getImage("src/Graphics/CabbageProjectile.png", 16, 16);
    public static Image spike = getImage("src/Graphics/spike.png", 16, 16);

    public ImageLibrary(GamePanel g){
        //peashooter
        imageList.put("ps", getImage("src/Graphics/Peashooter.png", 87, 40));
        xFix.put("ps", -20);
        yFix.put("ps", 0);
        textXFix.put("ps", 15);
        textYFix.put("ps", 24);
        tagToCard.put("ps", new Card("ps", 5, g));

        //walnut
        imageList.put("wn", getImage("src/Graphics/Walnut.png", 33, 40));
        xFix.put("wn", 7);
        yFix.put("wn", 4);
        textXFix.put("wn", 20);
        textYFix.put("wn", 35);
        tagToCard.put("wn", new Card("wn", 15, g));

        // chomper
        imageList.put("ch", getImage("src/Graphics/transparent-chomper2.png", 80,80));
        xFix.put("ch", -15);
        yFix.put("ch", -15);
        textXFix.put("ch", 15);
        textYFix.put("ch", 35);
        tagToCard.put("ch", new Card("ch", 15, g));

        //sunflower
        imageList.put("sf", getImage("src/Graphics/Sunflower.png", 65, 53));
        xFix.put("sf", -8);
        yFix.put("sf", -5);
        textXFix.put("sf", 15);
        textYFix.put("sf", 35);
        tagToCard.put("sf", new Card("sf", 5, g));

        //cabbagepult
        imageList.put("cp", getImage("src/Graphics/cabbage.png", 50, 50));
        xFix.put("cp", -5);
        yFix.put("cp", 0);
        textXFix.put("cp", 15);
        textYFix.put("cp", 35);
        tagToCard.put("cp", new Card("cp", 5, g));

        //Jalapeno
        imageList.put("ja", getImage("src/Graphics/Jalapeno.png", 28, 49));
        xFix.put("ja", 10);
        yFix.put("ja", 0);
        textXFix.put("ja", 15);
        textYFix.put("ja", 35);
        tagToCard.put("ja", new Card("ja", 30, g));

        //fumeShroom
        imageList.put("fs", getImage("src/Graphics/fumeShroom2.png", 48, 48));
        xFix.put("fs", 2);
        yFix.put("fs", 0);
        textXFix.put("fs", 15);
        textYFix.put("fs", 35);
        tagToCard.put("fs", new Card("fs", 10, g));

        //ice pea shooter
        imageList.put("ips", getImage("src/Graphics/IcePeaShooter.png", 48, 46));
        xFix.put("ips", 0);
        yFix.put("ips", 0);
        textXFix.put("ips", 15);
        textYFix.put("ips", 24);
        tagToCard.put("ips", new Card("ips", 0, g));

        //kernel
        imageList.put("kp", getImage("src/Graphics/kernel.png", 48, 48));
        xFix.put("kp", 2);
        yFix.put("kp", 0);
        textXFix.put("kp", 15);
        textYFix.put("kp", 35);
        tagToCard.put("kp", new Card("kp", 5, g));

        // Torchwood
        imageList.put("tw", getImage("src/Graphics/torchwoods.png", 48, 48));
        xFix.put("tw", 2);
        yFix.put("tw", 0);
        textXFix.put("tw", 15);
        textYFix.put("tw", 35);
        tagToCard.put("tw", new Card("tw", 5, g));

        //potatomine unarmed
        imageList.put("upm", getImage("src/Graphics/UnarmedPotatoMine.png", 78, 63));
        xFix.put("upm", -16);
        yFix.put("upm", 0);
        textXFix.put("upm", 15);
        textYFix.put("upm", 35);

        //potatomine armed
        imageList.put("pm", getImage("src/Graphics/PotatoMine.png", 49, 39));
        xFix.put("pm", 0);
        yFix.put("pm", 10);
        textXFix.put("pm", 15);
        textYFix.put("pm", 35);
        tagToCard.put("pm", new Card("pm", 50, g));

        // repeater
        imageList.put("rp", getImage("src/Graphics/Repeater.png", 38, 38));
        xFix.put("rp",5);
        yFix.put("rp", 5);
        textXFix.put("rp", 15);
        textYFix.put("rp", 35);
        tagToCard.put("rp", new Card("rp", 10, g));

        //gatlingpea
        imageList.put("gp", getImage("src/Graphics/GatlingPea.png", 48, 39));
        xFix.put("gp",0);
        yFix.put("gp", 5);
        textXFix.put("gp", 15);
        textYFix.put("gp", 35);
        tagToCard.put("gp", new Card("gp", 10, g));

        //Cherry Bomb
        imageList.put("cb", getImage("src/Graphics/CherryBomb.png", 57, 44));
        xFix.put("cb", -5);
        yFix.put("cb", 5);
        textXFix.put("cb", 15);
        textYFix.put("cb", 35);
        tagToCard.put("cb", new Card("cb", 30, g));

        //Ice Shroom
        imageList.put("is", getImage("src/Graphics/IceShroom.png", 50, 44));
        xFix.put("is", 0);
        yFix.put("is", 5);
        textXFix.put("is", 15);
        textYFix.put("is", 35);
        tagToCard.put("is", new Card("is", 0, g));

        //Cat tail
        imageList.put("ct", getImage("src/Graphics/cattail.png", 45, 45));
        xFix.put("ct", 0);
        yFix.put("ct", 5);
        textXFix.put("ct", 15);
        textYFix.put("ct", 35);
        tagToCard.put("ct", new Card("ct", 0, g));

        // Cob Cannon
        imageList.put("cc", getImage("src/Graphics/cobcannon.png", 45, 45 * 538 / 349));
        xFix.put("cc", 0);
        yFix.put("cc", 5);
        textXFix.put("cc", 15);
        textYFix.put("cc", 35);
        tagToCard.put("cc", new Card("cc", 0, g));

    }

    public static Image getImage(String path, int xScale, int yScale){
        BufferedImage tempImage;
        Image finalImage;

        try {
            tempImage = ImageIO.read(new File(path));
            finalImage = tempImage.getScaledInstance(xScale, yScale, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalImage;
    }

    public Image getImage(String key){
        return this.imageList.get(key);
    }

    public int getXFix(String key){
        return this.xFix.get(key);
    }

    public int getYFix(String key){
        return this.yFix.get(key);
    }

    public int getTextXFix(String key){return this.textXFix.get(key);}

    public int getTextYFix(String key){return this.textYFix.get(key);}

    public Card getCard(String key) {
        return this.tagToCard.get(key);
    }

    public Map<String, Image> getImageList() {
        return imageList;
    }

    public Map<String, Card> getTagToCard() {
        return tagToCard;
    }
}
