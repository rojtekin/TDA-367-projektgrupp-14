package model.rewards;

import model.IPlayerCharacter;
import model.IPlayerSubject;


public class RewardSystem {

    /**
     * checks if the playerCharacter has enough experience to level up
     * @param playerCharacter the playerCharacter that holds experience
     * @return true if experience is higher than or equals EXPERIENCE_THRESHOLD otherwise false
     */
    public boolean levelUpChecker(IPlayerSubject playerCharacter){
        return playerCharacter.getExperience() >= playerCharacter.getExperienceThreshold();
    }

    /**
     * Generates a random level up reward, if a perk has already been applied then the reward can't be a perk
     * @return a randomised reward
     */
    public Reward getRandomReward(IPlayerSubject playerCharacter){
        Reward reward = Reward.randomReward();
        if ((!playerCharacter.getPerkList().isEmpty()) && (((reward == Reward.GLASS_CANNON) || (reward == Reward.TANK) || (reward == Reward.SPEED_DEVIL)))){
            reward = getRandomReward(playerCharacter);
        }
        return reward;
    }

    /**
     * Applies a reward on a PlayerCharacter, if its a perk it gets added to perkList in player
     * @param playerCharacter the playerCharacter it gets applied to
     * @param reward the reward that gets applied
     */
    public void applyReward(IPlayerSubject playerCharacter, Reward reward){
        if (reward == Reward.TANK || reward == Reward.GLASS_CANNON || reward == Reward.SPEED_DEVIL){
            playerCharacter.getPerkList().add(reward);}
        playerCharacter.addTweak(reward.getTweak());

    }
}
