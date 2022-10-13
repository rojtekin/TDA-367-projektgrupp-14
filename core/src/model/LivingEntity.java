package model;

import com.dongbat.jbump.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Parent class for all entities that have movement and health
 */
public abstract class LivingEntity extends Entity {
    private float speed;
    private boolean inMotion = false;
    private float maxHealth;
    private float currentHealth;
    private float collisionDamage;
    private List<MovementListener> movementListeners = new ArrayList<>();
    private String faction;

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

    private void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getCurrentHealth() {return currentHealth;}

    public void setCurrentHealth(float currentHealth) {this.currentHealth = currentHealth;}

    public float getCollisionDamage() {
        return collisionDamage;
    }

    public void setFaction (String faction) {
        this.faction = faction;
    }

    public String getFaction() {
        return faction;
    }

    public LivingEntity(float x, float y, float height, float width, float speed, float health, float collisionDamage, String faction, World<Entity> world) {
        super(x, y, height, width, world);
        this.speed = speed;
        this.maxHealth = health;
        this.currentHealth = health;
        this.collisionDamage = collisionDamage;
        this.faction = faction;
    }

    /**
     * Moves the entity up.
     */
    public void moveUp() {
        setDirection(Direction.UP);
        moveForward();
    }
    /**
     * Moves the entity down.
     */
    public void moveDown() {
        setDirection(Direction.DOWN);
        moveForward();
    }
    /**
     * Moves the entity to the right.
     */
    public void moveRight() {
        setDirection(Direction.RIGHT);
        moveForward();
    }
    /**
     * Moves the entity to the left.
     */
    public void moveLeft() {
        setDirection(Direction.LEFT);
        moveForward();
    }

    /**
     * Moves the entity in the direction it is facing.
     */
    public void moveForward() {
        Response.Result result = move((getDirection().x * getSpeed()), (getDirection().y * getSpeed()));
        damageTouched(result.projectedCollisions);
        for (MovementListener movementListener : movementListeners) {
            movementListener.onMovement(result.projectedCollisions);
        };
        setMoving(true);
    }

    public void addMovementListener(MovementListener movementListener) {
        movementListeners.add(movementListener);
    }

    /**
     * Decreases currenthealth with a specified amount
     * @param damage amount to decrease health with
     */
    public void takeDamage(float damage) {
        if (currentHealth > 0) {
            currentHealth -= damage;
        }
    }

    /**
     * Pushes the entity back in a certain direction depending on the collision normal.
     * @param collisionNormal the collision normal
     */
    public void pushBack(IntPoint collisionNormal) {
        int distancePushed = 16;
        move((-collisionNormal.x * distancePushed), (-collisionNormal.y * distancePushed));
    }

    /**
     * Moves the collision box, then moves the entity to match it.
     * @param deltaX the distance in the x-direction that the entity should move
     * @param deltaY the distance in the y-direction that the entity should move
     * @return result
     */
    public Response.Result move(float deltaX, float deltaY) {
        Response.Result result = getWorld().move(getBoundingbox(), getX() + deltaX,getY() + deltaY, getMovementCollision());
        updatePosition();
        return result;
    }

    /**
     * Damages every touched hostile entity
     */
    private void damageTouched(Collisions projectedCollisions) {
        IDamageVisitor v = new DamageVisitor();
        for (int i = 0; i < projectedCollisions.size(); i++) {
            Item<Entity> touched = projectedCollisions.get(i).other;
            touched.userData.beAttacked(v, collisionDamage, faction);
        }
    }
}
