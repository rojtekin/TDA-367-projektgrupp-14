package Model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import java.util.ArrayList;

public abstract class Entity {
    private float x;
    private float y;
    private float height;
    private float width;
    private float speed;
    private float health;
    private boolean inMotion = false;
    private String entityName;
    private float maxHealth;
    private float currentHealth;
    private Item<Entity> boundingbox;
    private World<Entity> world;
    private CollisionFilter collisionType = CollisionFilter.defaultFilter;
    private Direction direction;

    public CollisionFilter getCollisionType() {
        return collisionType;
    }
    public void setCollisionType (CollisionFilter collisionType) {
        this.collisionType = collisionType;
    }

    //TODO rework movement, remove has-dependency on world
    public Entity(float x, float y, float height, float width, float speed,float health, World<Entity> world) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.maxHealth = health;
        this.currentHealth = health;
        this.direction = Direction.DOWN;
        setWorld(world);
    }

    public boolean isMoving() {
        return inMotion;
    }

    public void setMoving(boolean moving) {
        this.inMotion = moving;
    }

    protected void setEntityName(String name){
        this.entityName = name;
    }
    public String getEntityName(){
        return this.entityName;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getSpeed() {
        return speed;
    }
    protected void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    private void setMaxHealth(float maxHealth) {
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

    public World<Entity> getWorld() {
        return world;
    }

    /**
     * Adds a reference to the world that the player is in and
     * registers itself as a collisionbox
     */
    public void setWorld (World<Entity> world) {
        this.world = world;
        addCollision();
    }

    private void addCollision() {
        boundingbox = world.add(new Item<>(this), getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Sets the entity coordinates to match its collisionbox
     */
    public void updatePosition() {
        setX(world.getRect(boundingbox).x);
        setY(world.getRect(boundingbox).y);
    }

    /**
     * Moves the collisionbox up, then moves the entity to match it
     */
    public void moveUp(){
        world.move(boundingbox, getX(), getY() + getSpeed(), CollisionFilter.defaultFilter);
        updatePosition();
        setMoving(true);
        setDirection(Direction.UP);
    }
    /**
     * Moves the collisionbox down, then moves the entity to match it
     */
    public void moveDown(){
        world.move(boundingbox, getX(), getY() - getSpeed(), CollisionFilter.defaultFilter);
        updatePosition();
        setMoving(true);
        setDirection(Direction.DOWN);
    }
    /**
     * Moves the collisionbox to the right, then moves the entity to match it
     */
    public void moveRight(){
        world.move(boundingbox, getX() + getSpeed(), getY(), CollisionFilter.defaultFilter);
        updatePosition();
        setMoving(true);
        setDirection(Direction.RIGHT);
    }
    /**
     * Moves the collisionbox to the left, then moves the entity to match it
     */
    public void moveLeft(){
        world.move(boundingbox, getX() - getSpeed(), getY(), CollisionFilter.defaultFilter);
        updatePosition();
        setMoving(true);
        setDirection(Direction.LEFT);
    }
}
