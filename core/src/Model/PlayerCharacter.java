package Model;

import com.dongbat.jbump.World;

import java.util.WeakHashMap;

public class PlayerCharacter extends Entity implements IControllable {


    public PlayerCharacter(int spawnX, int spawnY, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, world);
        addCollision();
    }
}
