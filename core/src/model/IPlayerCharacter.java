package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dongbat.jbump.IntPoint;
import model.rewards.LivingTrait;
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

    /**
     * Used to test and see if tweaks are being properly added
     * @return a copy of the map holding the tweaks
     */
    Map<LivingTrait, ArrayList<Tweak>> getTweaks();

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
