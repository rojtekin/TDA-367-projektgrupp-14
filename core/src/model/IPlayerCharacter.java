package model;

import java.util.List;
import java.util.Set;

import com.dongbat.jbump.IntPoint;
import model.rewards.Reward;
import model.rewards.Tweak;

/**
 * Represents what i playerCharacter has
 */
public interface IPlayerCharacter {

    boolean isMoving();

    Direction getDirection();

    int getExperience();

    int getLevel();

    float getCurrentHealth();

    float getSpeed();

    float getDamage();

    /**
     * Gains a certain amount of experience
     * @param experience the amount experience that will be added
     */
    void gainExperience(int experience);

    /**
     * Sets the experience to zero and adds any experience overflow from last level
     */
    void reduceExperience();

    /**
     * Increases currentHealth by a certain amount
     * @param amount How much currentHealth increases by
     */
    void increaseCurrentHealth(float amount);

    int getExperienceThreshold();

    float getMaxHealth();

    float getX();

    float getY();

    /**
     * Moves the character in a certain direction
     * @param direction Enum that decides which direction to go
     * @param speed float that decides the speed it moves in that direction
     */
    void move(Direction direction, float speed);

    float getHeight();

    float getWidth();

    void setMoving(boolean moving);

    /**
     * Pushes the entity back in a certain direction depending on the collision normal.
     * @param normal x and y multipliers to normal pushback of 16
     */
    void pushBack(IntPoint normal);

    /**
     * @param tweaks adds a tweak to the playerCharacter used by rewardSystem
     */
    void addTweak(Set<Tweak> tweaks);

    /**
     * Swings the weapon
     * @param rotationStart the startangle the weapon is swung from
     * @param rotationFinish the endangle of the swing
     */
    void weaponAttack(int rotationStart, int rotationFinish);

    void setSwinging(boolean b);

    boolean isSwinging();

    PlayerWeapon getWeapon();

    boolean levelUpCheck();

    /**
     * increases the level of playerCharacter
     */
    void increaseLevel();

    /**
     * @return returns a list of all perks applied to the playerCharacter
     */
    List<Reward> getPerkList();
}
