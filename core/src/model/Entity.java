package model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public abstract class Entity implements IEntity{
    private float x;
    private float y;
    private float height;
    private float width;
    private float speed;
    private float health;
    private float damage;
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
    public Entity(float x, float y, float height, float width, float speed,float maxHealth ,float damage, World<Entity> world) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.damage = damage;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.direction = Direction.DOWN;
        setWorld(world);
    }

    public boolean isMoving() {
        return inMotion;
    }

    public void setMoving(boolean moving) { //TODO fix the same way as in Model TODO
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

    protected void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    protected void setY(float y) {
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

    protected void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getCurrentHealth() {return currentHealth;}

    public void setCurrentHealth(float currentHealth) {this.currentHealth = currentHealth;}

    public float getDamage() {
        return damage;
    }
    protected void setDamage(float damage) {
        this.damage = damage;
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction direction) {
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
        boundingbox = world.add(new Item<>(this), x, y, width, height);
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
        world.move(boundingbox, x, y + speed, CollisionFilter.defaultFilter);
        updatePosition();
        setMoving(true);
        direction = Direction.UP;
    }
    /**
     * Moves the collisionbox down, then moves the entity to match it
     */
    public void moveDown(){
        world.move(boundingbox, x, y - speed, CollisionFilter.defaultFilter);
        updatePosition();
        inMotion = true;
        direction = Direction.DOWN;
    }
    /**
     * Moves the collisionbox to the right, then moves the entity to match it
     */
    public void moveRight(){
        world.move(boundingbox, x + speed, y, CollisionFilter.defaultFilter);
        updatePosition();
        inMotion = true;
        direction = Direction.RIGHT;
    }
    /**
     * Moves the collisionbox to the left, then moves the entity to match it
     */
    public void moveLeft(){
        world.move(boundingbox, x - speed, y, CollisionFilter.defaultFilter);
        updatePosition();
        inMotion = true;
        direction = Direction.LEFT;
    }
}
