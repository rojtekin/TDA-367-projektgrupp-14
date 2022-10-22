package model.rewards;

import java.util.*;

/**
 * ENUM class of possible rewards when leveling up and the associated effect connected to them
 */
public enum Reward {
    /*COOL_DOWN_DECREASE,
    ABILITY_POWER_INCREASE,*/ //not implemented yet
    DAMAGE_INCREASE(new Tweak(LivingTrait.DAMAGE, 1, 1)),
    HEALTH_INCREASE(new Tweak(LivingTrait.HEALTH, 1, 1)),
    SPEED_INCREASE(new Tweak(LivingTrait.SPEED, 1, 1)),
    GLASS_CANNON(new Tweak(LivingTrait.HEALTH, 0.75f, 0), new Tweak(LivingTrait.DAMAGE, 1.5f, 0)),
    SPEED_DEVIL(new Tweak(LivingTrait.HEALTH, 0.75f, 0), new Tweak(LivingTrait.SPEED, 1.5f, 0)),
    TANK(new Tweak(LivingTrait.HEALTH, 1.5f, 0), new Tweak(LivingTrait.DAMAGE, 0.75f, 0));

    private Set<Tweak> tweaks = new HashSet<>();
    Reward(Tweak... tweaks) {
        this.tweaks = Set.of(tweaks);
    }

    private static final List<Reward> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * @return returns a random reward
     */
    public static Reward randomReward() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    /**
     * @return returns the correlated set of Tweak from the reward
     */
    public Set<Tweak> getTweak() {
        return this.tweaks;
    }


}