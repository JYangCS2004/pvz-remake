package model.Plant;

import model.EffectManager;
import model.Entity;
import model.Plant.plants.Pumpkin;
import model.StatusEffect.Effects.AddedHealthEffect;
import model.StatusEffect.StatusEffect;
import ui.GamePanel;

import java.awt.*;
import java.awt.geom.Area;

public abstract class Plant extends Entity {

    protected int health;
    private final int cost;
    protected String tag;
    protected boolean upgradable;
    protected EffectManager stackedManager;

    public Plant(int x, int y, int health, String tag, GamePanel g, int cost) {
        super(x, y);
        super.g = g;
        this.health = health;
        this.tag = tag;
        speed = 0;
        width = g.getTileSize();
        height = g.getTileSize();
        this.cost = cost;

        super.row = y / g.getTileSize();
        stackedManager = new EffectManager(g);
    }

    public boolean mouseOver(int x, int y) {
        if (hasShield()) {
            Area pumpkin = new Area(new Rectangle(x + g.getTileSize() / 2, y, width, height / 2));
            pumpkin.contains(x, y);
        }
        return new Area(getBounds()).contains(x, y);
    }

    public void draw(Graphics g){
        draw(g, tag);
        /*g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health),
                x + this.g.getImageLibrary().getTextXFix(tag),
                y + this.g.getImageLibrary().getTextYFix(tag)); */
    }

    public void drawWithoutText(Graphics g){
        drawWithoutText(g, tag);
        /*g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white); */
    }

    public void draw(Graphics g, String tag){
        g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white);
        g.drawString(Integer.toString(health),
                x + this.g.getImageLibrary().getTextXFix(tag),
                y + this.g.getImageLibrary().getTextYFix(tag));

        StatusEffect se = stackedManager.getByTag("AHP");
        if (se != null) {
            ((AddedHealthEffect) se).getSource().draw(g);
        }
    }

    public void drawWithoutText(Graphics g, String tag){
        g.drawImage(this.g.getImageLibrary().getImage(tag),
                x + this.g.getImageLibrary().getXFix(tag),
                y + this.g.getImageLibrary().getYFix(tag), null);
        g.setColor(Color.white);

        StatusEffect se = stackedManager.getByTag("AHP");
        if (se != null) {
            ((AddedHealthEffect) se).getSource().draw(g);
        }
    }

    @Override
    public void update() {
        stackedManager.updateAll();
    };

    public void decreaseHealth(int damage){
        StatusEffect se = stackedManager.getByTag("AHP");
        if (se != null) {
            ((AddedHealthEffect) se).update(damage);
        }

        if (stackedManager.numOfEffects() == 0) {
            this.health -= damage;
        }
    }

    public int getHealth() {
        return this.health;
    }

    public int getCost() {
        return cost;
    }

    public String getTag(){
        return tag;
    }
    public String getPlantCondition(){
        return "na";
    }
    public boolean canBeEaten(){
        return true;
    }


    public void transferPumpkinStatus(Pumpkin p) {
        AddedHealthEffect eff = new AddedHealthEffect(this, p);
        stackedManager.add(eff);
    }

    public void setTimer(){}


    public void transfer(Plant other) {
        this.stackedManager = other.stackedManager;
    }

    public boolean isUpgradable() {
        return upgradable;
    }

    public boolean hasShield() {
        return stackedManager.contains("AHP");
    }


    public Pumpkin getShield() {
        return ((AddedHealthEffect) stackedManager.getByTag("AHP")).getSource();

    }

    public void removeShield() {
        stackedManager.removeByTag("AHP");
    }
}
