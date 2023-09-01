package model.projectiles.Pult.PultProjectiles;

import model.Entity;
import model.projectiles.Pult.Pult;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class  Melon extends Pult {

    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    final static int DAMAGE = 25;
    protected BufferedImage melon;


    double angularVelocity = 0;
    double angle = 0;
    double angularAcceleration = 0;
    private int initialX;
    private int moi = 300;

    public Melon(int x, int y, Entity owner, GamePanel g, int row) {
        super(x, y, WIDTH, HEIGHT, DAMAGE, row, owner, g);
        initialX = x;
        onHitEffects.add("MELON");
        try {
            melon = resize(ImageIO.read(new File("src/Graphics/Melon.png")), 28);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(Graphics g) {
        angularAcceleration = ACCELERATION * (x - initialX) / moi;
        angularVelocity += angularAcceleration;
        angle += angularVelocity;

        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), melon.getWidth() / 2, melon.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(melon, null), (int)x, (int)y, null);
        // g.drawImage(melon, (int)x, (int)y, null);
    }

    public static BufferedImage resize(BufferedImage img, int newHeight) {
        double scale = (double) newHeight / img.getHeight();
        BufferedImage scaledImg = new BufferedImage((int) Math.ceil(scale * img.getWidth()), newHeight, BufferedImage.TYPE_INT_ARGB);
        //AffineTransform at = new AffineTransform();
        //at.scale(scale, scale);
        //AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        //return scaleOp.filter(img, scaledImg);
        Graphics2D graphics2D = scaledImg.createGraphics();
        graphics2D.drawImage(img, 0, 0, (int) Math.ceil(scale * img.getWidth()), newHeight, null);
        graphics2D.dispose();
        return scaledImg;
    }
}
