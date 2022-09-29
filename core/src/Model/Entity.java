package Model;

import java.util.ArrayList;

public abstract class Entity {
private int x;
private int y;
private int height;
private int width;
private int speed;
private boolean inMotion = false;
private String entityName;
private int angle;
private float health;
private Direction direction;

    public Entity(int x, int y, int height, int width, int speed, float health, String entityName) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
        this.direction = Direction.DOWN;
        this.entityName = entityName;
    }

    public String getEntityName(){
        return this.entityName;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean getInMotion() {
        if (speed != 0)
            return false;
        else
            return true;
    }
}
