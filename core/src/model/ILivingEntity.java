package model;

import com.dongbat.jbump.IntPoint;

/**
 * an interface that allows there to be different entities that shares common traits
 */
public interface ILivingEntity {

    boolean isMoving();
    /**
     * Gives the speed of the livingEntity
     * @return the Speed of this
     */
    float getSpeed();

    /**
     * Gives the max health
     * @return the max health of this
     */
    float getMaxHealth();

    /**
     * Gives the current health
     * @return the current health of this
     */
    float getCurrentHealth();

    /**
     * Sets the currentHealth of this
     * @param currentHealth the new health
     */
    void setCurrentHealth(float currentHealth);

    /**
     * Sets the boolean moving which
     * @param moving true means moving, false means still.
     */
    void setMoving(boolean moving);

    /**
     * Gets the direction of the entity
     * @return the direction of this
     */
    Direction getDirection();

    /**
     * Pushes the entity back in a certain direction depending on the collision normal.
     * @param collisionNormal the collision normal
     */
    void pushBack(IntPoint collisionNormal);
}
