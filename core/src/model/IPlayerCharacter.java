package model;

import com.dongbat.jbump.IntPoint;

import java.util.List;

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
    void increaseLevel();
    void decreaseLevel();

    /**
     * @return a list of all perk names currently applied to the character
     */
    List<String> getPerkList();

    /**
     * checks if the playerCharacter has enough experience to level up
     * @return true if experience is higher than or equals the experience threshold otherwise false
     */
    boolean levelUpCheck();


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
