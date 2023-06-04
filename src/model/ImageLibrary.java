package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLibrary {

    protected Map<String, Image> imageList;
    protected Map<String, Integer> xFix;
    protected Map<String, Integer> yFix;
    public ImageLibrary(){
        imageList = new HashMap<>();
        xFix = new HashMap<>();
        yFix = new HashMap<>();

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

        //walnut
        imageList.put("wn", walnut);
        xFix.put("wn", 7);
        yFix.put("wn", 4);
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
}
