package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends Entity implements IControllable {

    public PlayerCharacter(float spawnX, float spawnY, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, world, "Player");
    }
}
