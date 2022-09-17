package Model;

import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.physics.MovementController;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class PlayerCharacter extends Creature implements IUpdateable {
    private static PlayerCharacter instance;

    public static PlayerCharacter instance() {
        if (instance == null)
            instance = new PlayerCharacter();

        return instance;
    }

    //Todo Add player character spritesheet
    private PlayerCharacter() {
        super("PlaceHolder");
    }

    public void update() {
    }

    protected IMovementController createMovementController() {
        // setup movement controller
        return new PlatformingMovementController<>(this);
    }
}
