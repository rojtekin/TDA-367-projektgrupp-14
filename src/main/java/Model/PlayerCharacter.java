package Model;

import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.IUpdateable;

@EntityInfo(width = 32, height = 32)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class PlayerCharacter extends Creature implements IUpdateable {

    private static PlayerCharacter instance;

    public static PlayerCharacter instance() {
        if (instance == null)
            instance = new PlayerCharacter();
        return instance;
    }

    private PlayerCharacter() {
        super("soldier");
    }

    public void moveUp(){
        instance().setY(getY() - 1);
        instance().setAngle(180);
    }

    public void moveDown(){
        instance().setY(getY() + 1);
        instance().setAngle(0);
    }

    public void moveLeft(){
        instance().setX(getX() - 1);
        instance().setAngle(270);
    }

    public void moveRight(){
        instance().setX(getX() + 1);
        instance().setAngle(90);
    }

    @Override public void update() {
        // reset the number of consecutive jumps when touching the ground
    }


}
