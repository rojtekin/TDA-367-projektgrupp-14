package model;

import com.dongbat.jbump.*;

/**
 * Abstract parent class for all entities that have movement and health
 */
public abstract class LivingEntity extends Entity implements ILivingEntity {
    private float speed;
    private boolean inMotion = false;
    private float maxHealth;
    private float currentHealth;
    private Faction faction;
    private Direction direction;

    public boolean isInMotion() {
        return inMotion;
    }

    public void setInMotion(boolean inMotion) {
        this.inMotion = inMotion;
    }

    public float getSpeed() {
        return speed;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getCurrentHealth() {return currentHealth;}

    public void setCurrentHealth(float currentHealth) {this.currentHealth = currentHealth;}

    public void setFaction (Faction faction) {
        this.faction = faction;
    }

    public Faction getFaction() {
        return faction;
    }

    public Direction getDirection() {
        return direction;
    }
    private void setDirection(Direction direction) {
        this.direction = direction;
    }

    public LivingEntity(float x, float y, float height, float width, float speed, float health, float damage, Faction faction, World<IEntity> world) {
        super(x, y, height, width, damage, world);
        this.speed = speed;
        this.maxHealth = health;
        this.currentHealth = health;
        this.faction = faction;
        this.direction = Direction.DOWN;
    }

    /**
     * Moves the collision box, then moves the entity to match it.
     * @param deltaX the distance in the x-direction that the entity should move
     * @param deltaY the distance in the y-direction that the entity should move
     * @return the collisions that occurred when moving
     */
    private Collisions changePosition(float deltaX, float deltaY) {
        Response.Result result = getWorld().move(getBoundingbox(), getX() + deltaX,getY() + deltaY, CollisionFilter.defaultFilter);
        updatePosition();
        return result.projectedCollisions;
    }

    /**
     * Moves the entity in the direction it is facing.
     * @return the collisions that occurred when moving
     */
    public Collisions moveForward() {
        setInMotion(true);
        return changePosition((getDirection().x * speed), (getDirection().y * speed));
    }

    /**
     * Decreases current health with a specified amount
     * Used to decrease an entity's health directly such as
     * when using a status effect, for combat use beAttacked
     * instead.
     * @param damage amount to decrease health with
     */
    public void takeDamage(float damage) {
            currentHealth -= damage;
    }

    /**
     * Pushes the entity back in a certain direction depending on the collision normal.
     * @param collisionNormal vector that multiplies the pushbacks x and y pushback
     */
    public void pushBack(IntPoint collisionNormal) {
        int distancePushed = 16;
        changePosition((-collisionNormal.x * distancePushed), (-collisionNormal.y * distancePushed));
    }

    /**
     * Moves the entity in the specified direction.
     * @param direction the direction that the entity should move in
     * @return the collisions that occurred when moving
     */
    public Collisions move(Direction direction) {
        setDirection(direction);
        return moveForward();
    }

    @Override
    public void beAttacked(float damage, Faction faction) {
        if (this.faction != faction) {
            takeDamage(damage);
        }
    }
}
