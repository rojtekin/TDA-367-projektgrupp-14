package model.rewards;

import com.dongbat.jbump.World;
import model.IEntity;
import model.IPlayerCharacter;
import model.Model;
import model.playerperks.GlassCannon;
import model.playerperks.SpeedDevil;
import model.playerperks.Tank;

public class RewardSystem {
    private World<IEntity> world;
    private Model model;

    /**
     * Initializes the RewardSystem class so that a perk can be applied
     * @param model the model it initializes on.
     */
    public void initialize(Model model) {
        this.world = model.getWorld();
        this.model = model;
    }

    /**
     * Generates a random level up reward, if a perk is already applied then the reward can't be a perk
     * @return the randomised reward
     */
    public Reward getRandomReward(){
        Reward reward = Reward.randomReward();
        boolean perkApplied = (model.getPlayer().getPerkList().size()>0); //TODO: remove first two conditions in if-statement when abilities are implemented
        if ((reward == Reward.COOL_DOWN_DECREASE)||(reward == Reward.ABILITY_POWER_INCREASE)||(reward == Reward.TANK && perkApplied )||(reward == Reward.GLASS_CANNON && perkApplied) || (reward == Reward.SPEED_DEVIL && perkApplied)){
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
        System.out.println(reward);
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
        }
        else if (reward == Reward.GLASS_CANNON){
            playerCharacter = new GlassCannon(playerCharacter, world);
            playerCharacter.increaseCurrentHealth(playerCharacter.getMaxHealth());
        }
        else if (reward == Reward.TANK){
            playerCharacter = new Tank(playerCharacter, world);
            playerCharacter.increaseCurrentHealth(playerCharacter.getMaxHealth());
        }
        return playerCharacter;
    }
}
