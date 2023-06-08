package model.projectiles.Pult;

import model.Entity;
import model.projectiles.Projectile;
import ui.GamePanel;

public abstract class Pult extends Projectile {
    public Pult(int x, int y, int width, int height, int speed, int damage, int lifetime, int row, Entity owner, GamePanel g){
        super(x,y,width,height,speed,damage,lifetime, owner, g);
        super.row = row;
    }
}
