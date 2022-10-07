package model.rewards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Rewards {
    COOL_DOWN_DECREASE,
    ABILITY_POWER_INCREASE,
    DAMAGE_INCREASE,
    HEALTH_INCREASE,
    SPEED_INCREASE,
    GLASS_CANNON,
    SPEED_DEVIL,
    TANK;

    private static final List<Rewards> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Rewards randomReward()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

