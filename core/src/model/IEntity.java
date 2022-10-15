package model;

import com.dongbat.jbump.IntPoint;
import com.dongbat.jbump.World;

public interface IEntity {
    boolean isMoving();

    float getX();

    float getY();

    float getHeight();

    float getWidth();

    float getSpeed();

    float getMaxHealth();

    float getCurrentHealth();

    void setCurrentHealth(float currentHealth);

    float getDamage();

    Direction getDirection();

    World<IEntity> getWorld();

    void move(Direction direction, float speed);

    void setMoving(boolean moving);

    void pushBack(IntPoint collisionNormal);
}