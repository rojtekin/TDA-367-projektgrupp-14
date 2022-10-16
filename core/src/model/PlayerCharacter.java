package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends PlayerCharacterAdapter implements IControllable, IPlayerCharacter {

    public PlayerCharacter(float spawnX, float spawnY, World<IEntity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10,1, world);
    }
}
