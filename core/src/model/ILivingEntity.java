package model;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.IntPoint;
/**
 * an interface that allows there to be different entities that shares common traits
 */
public interface ILivingEntity extends IEntity {
    boolean isInMotion();

    /**
     * Gives the speed of the livingEntity
     *
     * @return the Speed of this
     */
    float getSpeed();

    /**
     * Gives the max health
     *
     * @return the max health of this
     */
    float getMaxHealth();

    /**
     * Gives the current health
     *
     * @return the current health of this
     */
    float getCurrentHealth();

    /**
     * Sets the currentHealth of this
     *
     * @param currentHealth the new health
     */
    void setCurrentHealth(float currentHealth);

    /**
     * Sets the boolean InMotion
     *
     * @param inMotion true means in motion, false means still.
     */
    void setInMotion(boolean inMotion);

    /**
     * Gets the direction of the entity
     *
     * @return the direction of this
     */
    Direction getDirection();

    /**
     * Pushes the entity back in a certain direction depending on the collision normal.
     *
     * @param collisionNormal vector that multiplies the pushback in x and y direction
     */
    void pushBack(IntPoint collisionNormal);

    Faction getFaction();

    Collisions move(Direction direction);
}