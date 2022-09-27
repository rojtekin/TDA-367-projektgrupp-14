package Model;


import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class PlayerCharacter extends Entity implements IControllable, ICollisionEntity {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;


    public PlayerCharacter(World<Entity> world) {
        super(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 32, 32, 5, 10, world);
        addCollision();
    }
}
