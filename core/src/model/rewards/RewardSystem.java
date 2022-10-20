package model.rewards;

import com.dongbat.jbump.World;
import model.IEntity;
import model.IPlayerCharacter;
import model.playerperks.GlassCannon;
import model.playerperks.SpeedDevil;
import model.playerperks.Tank;

public class RewardSystem {
    private static final int EXPERIENCE_THRESHOLD = 100;
    private boolean perkApplied;
    private World<IEntity> world;

    /**
     * Initializes the RewardSystem class so that a perk can be applied
     * @param world the world it initializes on.
     */
    public void initialize(World<IEntity> world) {
        perkApplied = false;
        this.world = world;
    }

    /**
     * checks if the playerCharacter has enough experience to level up
     * @param playerCharacter the playerCharacter that holds experience
     * @return true if experience is higher than or equals EXPERIENCE_THRESHOLD otherwise false
     */
    public boolean levelUpChecker(IPlayerCharacter playerCharacter){
        return playerCharacter.getExperience() >= EXPERIENCE_THRESHOLD;
    }

    /**
     * Generates a random level up reward, if a perk is already applied then the reward can't be a perk
     * @return a randomised reward
     */
    public Reward getRandomReward(){
        Reward reward = Reward.randomReward();
        if ((reward == Reward.TANK && perkApplied )||(reward == Reward.GLASS_CANNON && perkApplied) || (reward == Reward.SPEED_DEVIL && perkApplied)){
            reward = getRandomReward();
        }
        return reward;
    }

    /**
     * Applies a reward on a PlayerCharacter
     * @param playerCharacter the playerCharacter it gets applied to
     * @param reward the reward that gets applied
     * @return the resulting PlayerCharacter with the reward applied
     */
    public IPlayerCharacter applyReward(IPlayerCharacter playerCharacter, Reward reward){
        playerCharacter.reduceExperience();
        if (reward == Reward.DAMAGE_INCREASE){
            playerCharacter.increaseDamage();
        }
        else if (reward == Reward.HEALTH_INCREASE){
            playerCharacter.increaseMaxHealth();
            playerCharacter.increaseCurrentHealth(1);
        }
        else if (reward == Reward.SPEED_INCREASE){
            playerCharacter.increaseSpeed();
        }
        else if (reward == Reward.ABILITY_POWER_INCREASE){
            playerCharacter.increaseAbilityPower();
        }
        else if (reward == Reward.COOL_DOWN_DECREASE && playerCharacter.getAbilityCoolDownMultiplier() != 0){
            playerCharacter.decreasedAbilityCoolDownMultiplier();
        }
        else if (reward == Reward.SPEED_DEVIL){
            playerCharacter = new SpeedDevil(playerCharacter, world);
            playerCharacter.increaseCurrentHealth(playerCharacter.getMaxHealth());
            perkApplied = true;
        }
        else if (reward == Reward.GLASS_CANNON){
            playerCharacter = new GlassCannon(playerCharacter, world);
            playerCharacter.increaseCurrentHealth(playerCharacter.getMaxHealth());
            perkApplied = true;
        }
        else if (reward == Reward.TANK){
            playerCharacter = new Tank(playerCharacter, world);
            playerCharacter.increaseCurrentHealth(playerCharacter.getMaxHealth());
            perkApplied = true;
        }
        return playerCharacter;
    }
}
