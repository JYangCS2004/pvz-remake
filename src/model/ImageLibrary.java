package model;

import javax.imageio.ImageIO;
import java.awt.*;
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
