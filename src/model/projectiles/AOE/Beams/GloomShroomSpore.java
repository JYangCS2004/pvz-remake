package model.projectiles.AOE.Beams;

import model.Entity;
import model.projectiles.AOE.Beam;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GloomShroomSpore extends Beam {
    private final static int DAMAGE = 15;
    private final BufferedImage spore;

    private final AffineTransformOp op_ne;
    private final AffineTransformOp op_n;
    private final AffineTransformOp op_nw;
    private final AffineTransformOp op_s;
    private final AffineTransformOp op_se;
    private final AffineTransformOp op_sw;
    private final AffineTransformOp op_w;

    public GloomShroomSpore(int x, int y, Entity owner, GamePanel g) {
        super(x-g.getTileSize()-1, y-g.getTileSize(), g.getTileSize() * 3+2, g.getTileSize()*3, DAMAGE, owner, false, g);
        try {
            spore = ImageIO.read(new File("src/Graphics/Spore.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        onHitEffects.add("PIERCING");

        // northeast rotation
        AffineTransform tx_ne = AffineTransform.getRotateInstance(45, spore.getWidth() / 2, spore.getHeight() / 2);
        op_ne = new AffineTransformOp(tx_ne, AffineTransformOp.TYPE_BILINEAR);

        //southeast rotation
        AffineTransform tx_se = AffineTransform.getRotateInstance(315, spore.getWidth() / 2, spore.getHeight() / 2);
        op_se = new AffineTransformOp(tx_se, AffineTransformOp.TYPE_BILINEAR);

        //south rotation
        AffineTransform tx_s = AffineTransform.getRotateInstance(270, spore.getWidth() / 2, spore.getHeight() / 2);
        op_s = new AffineTransformOp(tx_s, AffineTransformOp.TYPE_BILINEAR);

        //southwest rotation
        AffineTransform tx_sw = AffineTransform.getRotateInstance(225, spore.getWidth() / 2, spore.getHeight() / 2);
        op_sw = new AffineTransformOp(tx_sw, AffineTransformOp.TYPE_BILINEAR);

        //west rotation
        AffineTransform tx_w = AffineTransform.getRotateInstance(180, spore.getWidth() / 2, spore.getHeight() / 2);
        op_w = new AffineTransformOp(tx_w, AffineTransformOp.TYPE_BILINEAR);

        //northwest rotation
        AffineTransform tx_nw = AffineTransform.getRotateInstance(135, spore.getWidth() / 2, spore.getHeight() / 2);
        op_nw = new AffineTransformOp(tx_nw, AffineTransformOp.TYPE_BILINEAR);

        //north rotation
        AffineTransform tx_n = AffineTransform.getRotateInstance(90, spore.getWidth() / 2, spore.getHeight() / 2);
        op_n = new AffineTransformOp(tx_n, AffineTransformOp.TYPE_BILINEAR);
    }

    @Override
    public void draw(Graphics g) {
//        g.setColor(new Color(230,230,250));
//        g.fillRect(x, y, width, height);
//        System.out.println(x + " " + y + " " + width + " " + height);
//        g.setColor(Color.white);
        int t = super.g.getTileSize();
        Graphics2D g2 = (Graphics2D) g;

// Drawing the rotated image at the required drawing locations
        g2.drawImage(op_ne.filter(spore, null), x + t*2+ 5, y+5, null);
        g2.drawImage(spore, x + t*2+ 5, y+t, null);
        g2.drawImage(op_se.filter(spore, null), x + t*2+ 5, y+2*t+5, null);
        g2.drawImage(op_s.filter(spore, null), x + t+ 5, y+t*2+5, null);
        g2.drawImage(op_sw.filter(spore, null), x + 5, y+t*2+5, null);
        g2.drawImage(op_w.filter(spore, null), x + 5, y+t +5, null);
        g2.drawImage(op_nw.filter(spore, null), x + 5, y+5, null);
        g2.drawImage(op_ne.filter(spore, null), x + t+ 5, y+5, null);


    }
}
