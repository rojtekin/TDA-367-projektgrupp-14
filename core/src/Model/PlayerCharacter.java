package Model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends Entity implements IControllable, ICollisionEntity {


    public PlayerCharacter(int startX, int startY, IModel model) {
        super(startX, startY, 32, 32, 5, 10, model);
        addCollision();
    }
}
