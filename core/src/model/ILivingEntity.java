package model;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.IntPoint;

public interface ILivingEntity extends IEntity {

    boolean isInMotion();

    float getSpeed();

    float getMaxHealth();

    float getCurrentHealth();

    void setCurrentHealth(float currentHealth);

    void setInMotion(boolean inMotion);

    Direction getDirection();

    void pushBack(IntPoint collisionNormal);

    Faction getFaction();

    Collisions move(Direction direction);
}
