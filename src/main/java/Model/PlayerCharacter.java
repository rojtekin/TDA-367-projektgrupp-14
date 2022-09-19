package Model;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.physics.PhysicsEngine;

@EntityInfo(width = 32, height = 32)
@MovementInfo(velocity = 8) // Higher than 8 creates bugs in the library
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class PlayerCharacter extends Creature implements IUpdateable {

    private static PlayerCharacter instance;
    private PhysicsEngine physicsEngine = Game.physics();

    public static PlayerCharacter instance() {
        if (instance == null)
            instance = new PlayerCharacter();
        return instance;
    }

    private PlayerCharacter() {
        super("soldier");
    }

    public void moveUp(){
        physicsEngine.move(instance(), 180, instance.getVelocity().get());
    }

    public void moveDown(){
        physicsEngine.move(instance(), 0, instance.getVelocity().get());
    }

    public void moveLeft(){
        physicsEngine.move(instance(), 270, instance.getVelocity().get());
    }

    public void moveRight(){
        physicsEngine.move(instance(), 90, instance.getVelocity().get());
    }

    @Override public void update() {
        // reset the number of consecutive jumps when touching the ground
    }


}
