package model;

import java.util.List;
import java.util.Set;

import model.rewards.Reward;
import model.rewards.Tweak;

public interface IPlayerCharacter extends ILivingEntity {

    int getExperience();

    int getLevel();

    void gainExperience(int experience);

    void reduceExperience();

    void increaseCurrentHealth(float amount);

    int getExperienceThreshold();

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
