package Model;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.Collision;
import de.gurkenlabs.litiengine.physics.IMovementController;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

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

        KeyboardEntityController<PlayerCharacter> movementController = new KeyboardEntityController<>(this);
        movementController.addUpKey(KeyEvent.VK_UP);
        movementController.addDownKey(KeyEvent.VK_DOWN);
        movementController.addLeftKey(KeyEvent.VK_LEFT);
        movementController.addRightKey(KeyEvent.VK_RIGHT);


        this.setController(IMovementController.class, movementController);
    }

    @Override public void update() {
        // reset the number of consecutive jumps when touching the ground
    }


}
