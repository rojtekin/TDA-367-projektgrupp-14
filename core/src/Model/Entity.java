package Model;

import java.util.ArrayList;

public abstract class Entity {
private int x;
private int y;
private int height;
private int width;
private int speed;
private double health;
private boolean inMotion = false;
private String entityName;
private int angle;

    public Entity(int x, int y, int height, int width, int speed,double health) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
        setAngle(270);
    }

    protected void setEntityName(String name){
        this.entityName = name;
    }
    public String getEntityName(){
        return this.entityName;
    }

    public int getX() {
        return x;
    }
    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    protected void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }
    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }

    public boolean getInMotion(){
        return this.inMotion;
    }
    public void setInMotion (boolean statement){
        this.inMotion = statement;
    }

    public int getAngle() {
        return angle;
    }
    protected void setAngle(int angle) {
        this.angle = angle;
    }

}
