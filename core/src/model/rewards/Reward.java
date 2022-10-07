package model.rewards;

import model.Entity;
import model.PlayerCharacter;
import com.dongbat.jbump.World;
// Currently unused

// Decorator-pattern
public abstract class Reward extends PlayerCharacter implements IReward {

    public Reward(float spawnX, float spawnY, World<Entity> world) {
        super(spawnX, spawnY, world);
    }

    @Override
    public void apply() {

    }
}
