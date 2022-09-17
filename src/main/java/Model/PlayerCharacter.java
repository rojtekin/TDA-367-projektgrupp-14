package Model;

import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.physics.MovementController;

@EntityInfo(width = 32, height = 32)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 12, collisionBoxHeight = 8, collision = true)
public class PlayerCharacter extends Creature implements IUpdateable, IControllable {
    private static PlayerCharacter instance;

    public static PlayerCharacter instance() {
        if (instance == null)
            instance = new PlayerCharacter();

        return instance;
    }

    private PlayerCharacter() {
        super("soldier");
    }

    @Override
    public void update() {
    }

    public void moveUp() {
        float currentDy = this.getController(MovementController.class).getDy();
        this.getController(MovementController.class).setDy(currentDy - 1);
    }
    public void moveDown() {
        float currentDy = this.getController(MovementController.class).getDy();
        this.getController(MovementController.class).setDy(currentDy + 1);
    }
    public void moveLeft() {
        float currentDy = this.getController(MovementController.class).getDx();
        this.getController(MovementController.class).setDx(currentDy - 1);
    }
    public void moveRight() {
        float currentDy = this.getController(MovementController.class).getDx();
        this.getController(MovementController.class).setDx(currentDy + 1);
    }


}
