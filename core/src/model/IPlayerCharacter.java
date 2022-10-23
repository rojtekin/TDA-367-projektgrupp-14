package model;

import java.util.List;
import java.util.Set;

import com.dongbat.jbump.Collisions;
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

    int getExperienceThreshold();

    float getAbilityCoolDownMultiplier();

    float getMaxHealth();

    float getX();

    float getY();

    Collisions move(Direction direction);

    float getHeight();

    float getWidth();

    void setMoving(boolean moving);

    void pushBack(IntPoint normal);

    /**
     * @param tweaks adds a tweak to the playerCharacter used by rewardSystem
     */
    void addTweak(Set<Tweak> tweaks);

    void weaponAttack(int i, int i1, int i2);

    void setSwinging(boolean b);

    boolean isSwinging();

    PlayerWeapon getWeapon();

    boolean levelUpCheck();

    void increaseLevel();

    /**
     * @return returns a list of all perks applied to the playerCharacter
     */
    List<Reward> getPerkList();

    void beAttacked(float damage, Faction faction);
}
