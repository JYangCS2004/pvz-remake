package model;

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

    //some static images
    public static Image cabbage = getImage("src/Graphics/CabbageProjectile.png", 16, 16);

    public ImageLibrary(){
        //peashooter
        imageList.put("ps", getImage("src/Graphics/Peashooter.png", 87, 40));
        xFix.put("ps", -20);
        yFix.put("ps", 0);
        textXFix.put("ps", 15);
        textYFix.put("ps", 24);

        //walnut
        imageList.put("wn", getImage("src/Graphics/Walnut.png", 33, 40));
        xFix.put("wn", 7);
        yFix.put("wn", 4);
        textXFix.put("wn", 20);
        textYFix.put("wn", 35);

        // chomper
        imageList.put("ch", getImage("src/Graphics/transparent-chomper2.png", 80,80));
        xFix.put("ch", -15);
        yFix.put("ch", -15);
        textXFix.put("ch", 15);
        textYFix.put("ch", 35);

        //sunflower
        imageList.put("sf", getImage("src/Graphics/Sunflower.png", 65, 53));
        xFix.put("sf", -8);
        yFix.put("sf", -5);
        textXFix.put("sf", 15);
        textYFix.put("sf", 35);

        //cabbagepult
        imageList.put("cp", getImage("src/Graphics/cabbage.png", 50, 50));
        xFix.put("cp", -5);
        yFix.put("cp", 0);
        textXFix.put("cp", 15);
        textYFix.put("cp", 35);

        //Jalapeno
        imageList.put("ja", getImage("src/Graphics/Jalapeno.png", 28, 49));
        xFix.put("ja", 10);
        yFix.put("ja", 0);
        textXFix.put("ja", 15);
        textYFix.put("ja", 35);

        //fumeShroom
        imageList.put("fs", getImage("src/Graphics/fumeShroom2.png", 48, 48));
        xFix.put("fs", 2);
        yFix.put("fs", 0);
        textXFix.put("fs", 15);
        textYFix.put("fs", 35);

        //ice pea shooter
        imageList.put("ips", getImage("src/Graphics/IcePeaShooter.png", 48, 46));
        xFix.put("ips", 0);
        yFix.put("ips", 0);
        textXFix.put("ips", 15);
        textYFix.put("ips", 24);
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

}
