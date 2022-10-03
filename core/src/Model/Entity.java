package Model;

public abstract class Entity {
private int x;
private int y;
private int height;
private int width;
private int speed;
private boolean inMotion = false;
private String entityName;
private int angle;
private float maxHealth;
private float currentHealth;
private Direction direction;

    public Entity(int x, int y, int height, int width, int speed, float health) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.maxHealth = health;
        this.currentHealth = health;
        this.direction = Direction.DOWN;
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

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getCurrentHealth() {return currentHealth;}

    public void setCurrentHealth(float currentHealth) {this.currentHealth = currentHealth;}

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
