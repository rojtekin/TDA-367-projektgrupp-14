package model;

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


    float getAbilityCoolDownMultiplier();

    float getMaxHealth();

    float getX();

    float getY();

    void moveLeft();

    void moveRight();

    void moveUp();

    void moveDown();

    float getHeight();

    float getWidth();

    void setMoving(boolean moving);
}
