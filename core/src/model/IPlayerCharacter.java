package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dongbat.jbump.IntPoint;
import model.rewards.LivingTrait;
import model.rewards.Reward;
import model.rewards.Tweak;

/**
 * Represents a playerCharacter
 */
public interface IPlayerCharacter extends ILivingEntity {

    int getExperience();

    int getLevel();

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

    /**
     * @param tweaks adds a tweak to the playerCharacter used by rewardSystem
     */
    void addTweak(Set<Tweak> tweaks);


    /**
     * Used to test and see if tweaks are being properly added
     * @return a copy of the map holding the tweaks
     */
    Map<LivingTrait, ArrayList<Tweak>> getTweaks();

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
