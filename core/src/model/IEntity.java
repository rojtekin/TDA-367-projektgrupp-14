package model;

import com.dongbat.jbump.World;

/**
 * Interface symbolizing an entity
 */
public interface IEntity {

    float getX();

    float getY();

    float getHeight();

    float getWidth();

    float getDamage();

    World<IEntity> getWorld();

    /**
     * method
     * @param damage the amount of damage this should take
     * @param faction the faction of the attacker. Determines what effect is applied to the target
     */
    void beAttacked(float damage, Faction faction);
}