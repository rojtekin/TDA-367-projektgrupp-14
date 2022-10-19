package model;

import com.dongbat.jbump.IntPoint;

public interface IPlayerCharacter {

    boolean isMoving();
    Direction getDirection();
    int getExperience();
    int getLevel();
    float getCurrentHealth();
    float getSpeed();
    float getDamage();
    float getAbilityPower();
    void increaseMaxHealth();
    void decreaseMaxHealth();
    void increaseSpeed();
    void decreaseSpeed();
    void increaseDamage();
    void decreaseDamage();
    void increasedAbilityCoolDownMultiplier();
    void decreasedAbilityCoolDownMultiplier();
    void increaseAbilityPower();
    void decreaseAbilityPower();
    void gainExperience(int experience);
    void reduceExperience();
    void increaseCurrentHealth(float amount);


    float getAbilityCoolDownMultiplier();

    float getMaxHealth();

    float getX();

    float getY();

    void move(Direction direction, float speed);

    float getHeight();

    float getWidth();

    void setMoving(boolean moving);

    void pushBack(IntPoint normal);

    void weaponAttack(int i, int i1, int i2);

    void setSwinging(boolean b);

    boolean isSwinging();

    PlayerWeapon getWeapon();
}
