package model.projectiles.AOE;

import model.Entity;
import model.projectiles.Projectile;
import ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public abstract class Beam extends Projectile {

    private boolean boom;
    public Beam(int x, int y, int width, int height, int damage, Entity owner, boolean boom, GamePanel g){
        super(x,y,width,height, 0,damage, 1, owner, new ArrayList(), g);
        this.boom = boom;
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public boolean getBoom(){
        return boom;
    }
}
