package model;

import model.playerperks.SpeedDevil;
import model.rewards.Rewards;

public class RewardSystem {

    public RewardSystem() {
        initialize();
    }

    public void initialize() {
    }
    protected boolean levelUpChecker(IPlayerCharacter playerCharacter){
        return playerCharacter.getExperience() >= 100;
    }

    protected IPlayerCharacter applyReward(IPlayerCharacter playerCharacter){
        Rewards reward = Rewards.randomReward();
        if (reward == Rewards.DAMAGE_INCREASE){
            playerCharacter.increaseDamage();
        }
        if (reward == Rewards.HEALTH_INCREASE){
            playerCharacter.increaseMaxHealth();
        }
        if (reward == Rewards.SPEED_INCREASE){
            playerCharacter.increaseSpeed();
        }
        if (reward == Rewards.ABILITY_POWER_INCREASE){
            playerCharacter.increaseAbilityPower();
        }
        if (reward == Rewards.COOL_DOWN_DECREASE && playerCharacter.getAbilityCoolDownMultiplier() != 0){
            playerCharacter.decreasedAbilityCoolDownMultiplier();
        }
        if (reward == Rewards.SPEED_DEVIL){
            playerCharacter = new SpeedDevil(playerCharacter); //TODO vrf måste jag casta till PlayerCharacter
        }
        return playerCharacter;
    }
}
