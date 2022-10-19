package model;

import com.dongbat.jbump.IntPoint;

public interface ILivingEntity {

    boolean isMoving();

    float getSpeed();

    float getMaxHealth();

    float getCurrentHealth();

    void setCurrentHealth(float currentHealth);

    void setMoving(boolean moving);

    Direction getDirection();

    void pushBack(IntPoint collisionNormal);
}
