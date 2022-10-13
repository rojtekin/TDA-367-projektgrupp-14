package model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.IntPoint;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response.Result;
import com.dongbat.jbump.World;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Entity implements IEntity{
    private float x;
    private float y;
    private float height;
    private float width;
    protected float speed;
    private float health;
    private float damage;
    private boolean inMotion = false;
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

     public Entity(float x, float y, float height, float width, float speed, float maxHealth ,float damage, World<Entity> world) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.damage = damage;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.direction = Direction.DOWN;
        setWorld(Objects.requireNonNull(world));
    }

    public boolean isMoving() {
        return inMotion;
    }

    public void setMoving(boolean moving) {
        this.inMotion = moving;
    }

    public float getX() {
        return x;
    }

    private void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    private void setY(float y) {
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
    private void setWorld (World<Entity> world) {
        this.world = world;
        addCollision();
    }

    private void addCollision() {
        boundingbox = world.add(new Item<>(this), x, y, width, height);
    }

    /**
     * Sets the entity coordinates to match its collisionbox
     */
    private void updatePosition() {
        setX(world.getRect(boundingbox).x);
        setY(world.getRect(boundingbox).y);
    }

    /**
     * Moves the entity in the specified direction.
     * @param direction the direction that the entity should move in
     */
    public void move(Direction direction, Float speed) {
        setDirection(direction);
        moveForward(speed);
    }

    /**
     * Moves the entity in the direction it is facing.
     */
    public void moveForward(Float speed) {
        Result result = changePosition((direction.x * speed), (direction.y * speed));
        for (MovementListener movementListener : movementListeners) {
            movementListener.onMovement(result.projectedCollisions);
        }
        setMoving(true);
    }

    public void addMovementListener(MovementListener movementListener) {
        movementListeners.add(movementListener);
    }

    /**
     * Pushes the entity back in a certain direction depending on the collision normal.
     * @param collisionNormal the collision normal
     */
    public void pushBack(IntPoint collisionNormal) {
        int distancePushed = 16;
        changePosition((-collisionNormal.x * distancePushed), (-collisionNormal.y * distancePushed));
    }

    /**
     * Moves the collision box, then moves the entity to match it.
     * @param deltaX the distance in the x-direction that the entity should move
     * @param deltaY the distance in the y-direction that the entity should move
     * @return result
     */
    private Result changePosition(float deltaX, float deltaY) {
        Result result = world.move(boundingbox, this.x + deltaX,this.y + deltaY, CollisionFilter.defaultFilter);
        updatePosition();
        return result;
    }
}

