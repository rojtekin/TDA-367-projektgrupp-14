package model.rewards;

import model.IPlayerCharacter;
import model.playerperks.GlassCannon;
import model.playerperks.SpeedDevil;
import model.playerperks.Tank;

public class RewardSystem {
private boolean perkApplied;
    //TODO fråga:  ska jag göra om till lista istället för ENUM? för att få bort getRandomRewards stora if-sats

    public RewardSystem() {
        initialize();
    }

    public void initialize() {
        perkApplied = false;
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
        if (reward == Reward.DAMAGE_INCREASE){
            playerCharacter.increaseDamage();
        }
        if (reward == Reward.HEALTH_INCREASE){
            playerCharacter.increaseMaxHealth();
        }
        if (reward == Reward.SPEED_INCREASE){
            playerCharacter.increaseSpeed();
        }
        if (reward == Reward.ABILITY_POWER_INCREASE){
            playerCharacter.increaseAbilityPower();
        }
        if (reward == Reward.COOL_DOWN_DECREASE && playerCharacter.getAbilityCoolDownMultiplier() != 0){
            playerCharacter.decreasedAbilityCoolDownMultiplier();
        }
        if (reward == Reward.SPEED_DEVIL){
            playerCharacter = new SpeedDevil(playerCharacter);
            perkApplied = true;
        }
        if (reward == Reward.GLASS_CANNON){
            playerCharacter = new GlassCannon(playerCharacter);
            perkApplied = true;
        }
        if (reward == Reward.TANK){
            playerCharacter = new Tank(playerCharacter);
        }
        return playerCharacter;
    }
}
