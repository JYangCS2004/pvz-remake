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

    public ImageLibrary(){
        Image walnut = null;
        try{
            walnut = ImageIO.read(new File("src/Graphics/Walnut.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Image peaShooter = null;
        try{
            peaShooter = ImageIO.read(new File("src/Graphics/Peashooter.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        BufferedImage chomper;
        Image resized;
        try {
            chomper = ImageIO.read(new File("src/Graphics/transparent-chomper2.png"));
            resized = chomper.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage sunflower;
        Image resizedSunflower;
        try {
            sunflower = ImageIO.read(new File("src/Graphics/Sunflower.png"));
            resizedSunflower = sunflower.getScaledInstance(65, 53, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage cabbagepult;
        Image resizedCabbagePult;
        try {
            cabbagepult = ImageIO.read(new File("src/Graphics/cabbage.png"));
            resizedCabbagePult = cabbagepult.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //peashooter
        imageList.put("ps", peaShooter);
        xFix.put("ps", -20);
        yFix.put("ps", 0);
        textXFix.put("ps", 15);
        textYFix.put("ps", 24);

        //walnut
        imageList.put("wn", walnut);
        xFix.put("wn", 7);
        yFix.put("wn", 4);
        textXFix.put("wn", 20);
        textYFix.put("wn", 35);

        // chomper
        imageList.put("ch", resized);
        xFix.put("ch", -15);
        yFix.put("ch", -15);
        textXFix.put("ch", 15);
        textYFix.put("ch", 35);

        //sunflower
        imageList.put("sf", resizedSunflower);
        xFix.put("sf", -8);
        yFix.put("sf", -5);
        textXFix.put("sf", 15);
        textYFix.put("sf", 35);

        //cabbagepult
        imageList.put("cp", resizedCabbagePult);
        xFix.put("cp", -5);
        yFix.put("cp", 0);
        textXFix.put("cp", 15);
        textYFix.put("cp", 35);

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
