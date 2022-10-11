package model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public abstract class Entity {
    private float x;
    private float y;
    private float height;
    private float width;
    private Item<Entity> boundingbox;
    private World<Entity> world;
    private CollisionFilter movementCollision = CollisionFilter.defaultFilter;
    private Direction direction;

    public CollisionFilter getMovementCollision() {
        return movementCollision;
    }
    public void setMovementCollision(CollisionFilter movementCollision) {
        this.movementCollision = movementCollision;
    }

    //TODO rework movement, remove has-dependency on world
    public Entity(float x, float y, float height, float width, World<Entity> world) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.direction = Direction.DOWN;
        setWorld(world);
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public World<Entity> getWorld() {
        return world;
    }

    public Item<Entity> getBoundingbox() {
        return boundingbox;
    }

    /**
     * Adds a reference to the world that the player is in and
     * registers itself as a collisionbox
     */
    public void setWorld (World<Entity> world) {
        this.world = world;
        addCollision();
    }

    /**
     * Adds its own collisionbox to the world
     */
    public void addCollision() {
        boundingbox = world.add(new Item<>(this), x, y, width, height);
    }

    /**
     * Removes its own collisionbox from the world
     */
    public void removeCollision() {
        world.remove(boundingbox);
    }

    /**
     * Sets the entity coordinates to match its collisionbox
     */
    public void updatePosition() {
        setX(world.getRect(boundingbox).x);
        setY(world.getRect(boundingbox).y);
    }
}
