package model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;
import java.util.Objects;

public abstract class Entity implements IEntity{
    private float x;
    private float y;
    private float height;
    private float width;
    private float damage;
    private Item<IEntity> boundingbox;
    private World<IEntity> world;
    private CollisionFilter collisionResponse = CollisionFilter.defaultFilter;

    public CollisionFilter getCollisionResponse() {
        return collisionResponse;
    }

    public void setCollisionResponse(CollisionFilter collisionResponse) {
        this.collisionResponse = collisionResponse;
    }

     public Entity(float x, float y, float height, float width, float damage, World<IEntity> world) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.damage = damage;
        setWorld(Objects.requireNonNull(world));
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

    public float getDamage() {
        return damage;
    }
    protected void setDamage(float damage) {
        this.damage = damage;
    }

    public Item<IEntity> getBoundingbox() {
        return boundingbox;
    }

    /**
     * Adds a reference to the world that the player is in and
     * registers itself as a collisionbox within it. Useful for
     * moving entities between different levels
     */
    public void setWorld (World<IEntity> world) {
        this.world = world;
        addCollision();
    }

    /**
     * Necessary for behaviour in child classes and tests
     * @return the reference to the entities world object,
     *         which is responsible for collision and movement
     */
    public World<IEntity> getWorld() {
        return world;
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

    /**
     * The concrete logic for what should happen when attacked
     * depends on the specific class of the receiver
     * @param damage The amount of damage inflicted
     * @param faction The faction of the attacker
     */
    public abstract void beAttacked(float damage, Faction faction);
}
