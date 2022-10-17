package model.rewards;

import com.dongbat.jbump.World;
import model.IEntity;
import model.IPlayerCharacter;
import model.playerperks.GlassCannon;
import model.playerperks.SpeedDevil;
import model.playerperks.Tank;

public class RewardSystem {
    private boolean perkApplied;
    private World<IEntity> world;

    public RewardSystem() {
        initialize(world);
    }

    public void initialize(World<IEntity> world) {
        perkApplied = false;
        this.world = world;
    }
    public boolean levelUpChecker(IPlayerCharacter playerCharacter){
        return playerCharacter.getExperience() >= 100;
    }

    public Reward getRandomReward(){
        Reward reward = Reward.randomReward();
        if ((reward == Reward.TANK && perkApplied )||(reward == Reward.GLASS_CANNON && perkApplied) || (reward == Reward.SPEED_DEVIL && perkApplied)){
            reward = getRandomReward();
        }
        return reward;
    }

    public IPlayerCharacter applyReward(IPlayerCharacter playerCharacter, Reward reward){
        playerCharacter.reduceExperience();
        if (reward == Reward.DAMAGE_INCREASE){
            playerCharacter.increaseDamage();
        }
        else if (reward == Reward.HEALTH_INCREASE){
            playerCharacter.increaseMaxHealth();
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
            perkApplied = true;
        }
        else if (reward == Reward.GLASS_CANNON){
            playerCharacter = new GlassCannon(playerCharacter);
            perkApplied = true;
        }
        else if (reward == Reward.TANK){
            playerCharacter = new Tank(playerCharacter);
            perkApplied = true;
        }
        return playerCharacter;
    }
}
