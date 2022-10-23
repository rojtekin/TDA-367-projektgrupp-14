package model;

import com.dongbat.jbump.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Parent class for all entities that have movement and health
 */
public abstract class LivingEntity extends Entity implements ILivingEntity {
    private float speed;
    private boolean inMotion = false;
    private float maxHealth;
    private float currentHealth;
    private final List<MovementListener> movementListeners = new ArrayList<>();
    private Faction faction;
    private Direction direction;

    public boolean isMoving() {
        return inMotion;
    }

    public void setMoving(boolean moving) {
        this.inMotion = moving;
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

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
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
     * @return result
     */
    private Response.Result changePosition(float deltaX, float deltaY) {
        Response.Result result = getWorld().move(getBoundingbox(), getX() + deltaX,getY() + deltaY, CollisionFilter.defaultFilter);
        damageTouched(result.projectedCollisions);
        updatePosition();
        return result;
    }

    /**
     * Moves the entity in the direction it is facing.
     * @param speed the distance you move.
     */
    public void moveForward(float speed) {
        Response.Result result = changePosition((getDirection().x * speed), (getDirection().y * speed));
        for (MovementListener movementListener : movementListeners) {
            movementListener.onMovement(result.projectedCollisions);
        }
        setMoving(true);
    }

    /**
     * adds a specific movementListener
     * @param movementListener movementListener that will be added
     */
    public void addMovementListener(MovementListener movementListener) {
        movementListeners.add(movementListener);
    }

    /**
     * Decreases current health with a specified amount
     * @param damage amount to decrease health with
     */
    public void takeDamage(float damage) {
        if (currentHealth > 0) {
            currentHealth -= damage;
        }
    }

    /**
     * Pushes the entity back in a certain direction depending on the collision normal.
     * @param collisionNormal x and y multipliers to normal pushback of 16
     */
    public void pushBack(IntPoint collisionNormal) {
        int distancePushed = 16;
        changePosition((-collisionNormal.x * distancePushed), (-collisionNormal.y * distancePushed));
    }

    /**
     * Moves the entity in the specified direction.
     * @param direction the direction that the entity should move in
     */
    public void move(Direction direction, float speed) {
        setDirection(direction);
        moveForward(speed);
    }

    /**
     * Damages every touched hostile enemy
     * @param projectedCollisions
     */
    private void damageTouched(Collisions projectedCollisions) {
        for (int i = 0; i < projectedCollisions.size(); i++) {
            Item<IEntity> touched = projectedCollisions.get(i).other;
            touched.userData.beAttacked(getDamage()/10, faction);
        }
    }

    @Override
    public void beAttacked(float damage, Faction faction) {
        if (this.faction != faction) {
            takeDamage(damage);
        }
    }
}
