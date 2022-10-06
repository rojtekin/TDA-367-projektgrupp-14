package Model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response.Result;
import com.dongbat.jbump.World;

import java.util.ArrayList;
import java.util.List;

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
    private List<MovementListener> movementListeners = new ArrayList<>();

    public CollisionFilter getCollisionType() {
        return collisionType;
    }
    public void setCollisionType (CollisionFilter collisionType) {
        this.collisionType = collisionType;
    }

    //TODO rework movement, remove has-dependency on world
    public Entity(float x, float y, float height, float width, float speed,float health, World<Entity> world, String entityName) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.maxHealth = health;
        this.currentHealth = health;
        this.direction = Direction.DOWN;
        this.entityName = entityName;
        setWorld(world);
    }

    public boolean isMoving() {
        return inMotion;
    }

    public void setMoving(boolean moving) {
        this.inMotion = moving;
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
     * Moves the entity up.
     */
    public void moveUp(){
        setDirection(Direction.UP);
        move();
    }
    /**
     * Moves the entity down.
     */
    public void moveDown(){
        setDirection(Direction.DOWN);
        move();
    }
    /**
     * Moves the entity to the right.
     */
    public void moveRight(){
        setDirection(Direction.RIGHT);
        move();
    }
    /**
     * Moves the entity to the left.
     */
    public void moveLeft(){
        setDirection(Direction.LEFT);
        move();
    }

    /**
     * Moves the entity in the direction it is facing. Moves the collision box, then moves the entity to match it.
     */
    public void move() {
        Result result = world.move(boundingbox, getX() + (direction.x * getSpeed()),
                getY() + (direction.y * getSpeed()), CollisionFilter.defaultFilter);
        for (MovementListener movementListener : movementListeners) {
            movementListener.onMovement(result.projectedCollisions);
        }
        updatePosition();
        setMoving(true);
    }

    public void addMovementListener(MovementListener movementListener) {
        movementListeners.add(movementListener);
    }
}
