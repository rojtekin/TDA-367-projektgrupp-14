package model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public interface IEntity {
    boolean isMoving();

    String getEntityName();

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

    boolean getInMotion();

    World<Entity> getWorld();

    void updatePosition();

    void moveUp();

    void moveDown();

    void moveRight();

    void moveLeft();

    void setMoving(boolean moving);
}