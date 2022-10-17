package model;

import com.dongbat.jbump.IntPoint;
import com.dongbat.jbump.World;

public interface IEntity {

    float getX();

    float getY();

    float getHeight();

    float getWidth();

    float getDamage();

    World<IEntity> getWorld();
}