package model.rewards;

import com.dongbat.jbump.World;
import model.IEntity;
import model.IPlayerCharacter;
import model.Model;

public class RewardSystem {
    private static final int EXPERIENCE_THRESHOLD = 100;
    private Model model;

    /**
     * Initializes the RewardSystem class so that a perk can be applied
     * @param model the Model it initializes on.
     */
    public void initialize(Model model) {
        this.model = model;
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
     * Generates a random level up reward, if a perk has already been applied then the reward can't be a perk
     * @return a randomised reward
     */
    public Reward getRandomReward(){
        Reward reward = Reward.randomReward();
        if ((reward == Reward.TANK && !model.getPlayer().getPerkList().isEmpty())||(reward == Reward.GLASS_CANNON && !model.getPlayer().getPerkList().isEmpty()) || (reward == Reward.SPEED_DEVIL && !model.getPlayer().getPerkList().isEmpty())){
            reward = getRandomReward();
        }
        return reward;
    }

    /**
     * Applies a reward on a PlayerCharacter, if its a perk it gets added to perkList in player
     * @param playerCharacter the playerCharacter it gets applied to
     * @param reward the reward that gets applied
     */
    public void applyReward(IPlayerCharacter playerCharacter, Reward reward){
        if (reward == Reward.TANK || reward == Reward.GLASS_CANNON || reward == Reward.SPEED_DEVIL){
            playerCharacter.getPerkList().add(reward);}
        playerCharacter.addTweak(reward.getTweak());

    }
}
