package model;

import java.util.List;
import java.util.Set;

import com.dongbat.jbump.IntPoint;
import model.rewards.Reward;
import model.rewards.Tweak;

public interface IPlayerCharacter {

    boolean isMoving();

    Direction getDirection();

    int getExperience();

    int getLevel();

    float getCurrentHealth();

    float getSpeed();

    float getDamage();

    void gainExperience(int experience);

    void reduceExperience();

    void increaseCurrentHealth(float amount);

    int getExperienceThreshold();

    float getMaxHealth();

    float getX();

    float getY();

    void move(Direction direction, float speed);

    float getHeight();

    float getWidth();

    void setMoving(boolean moving);

    void pushBack(IntPoint normal);

    /**
     * @param tweaks adds a tweak to the playerCharacter used by rewardSystem
     */
    void addTweak(Set<Tweak> tweaks);

    void weaponAttack(int rotationStart, int rotationFinish);

    void setSwinging(boolean b);

    boolean isSwinging();

    PlayerWeapon getWeapon();

    boolean levelUpCheck();

    void increaseLevel();

    /**
     * @return returns a list of all perks applied to the playerCharacter
     */
    List<Reward> getPerkList();
}
