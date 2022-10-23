package model.monsters;

import com.dongbat.jbump.Collisions;
import model.*;
import com.dongbat.jbump.World;
import application.Time;

public class Mouse extends Monster {
    private float timeWhenDirectionChanged = 0;

    /**
     * Creates a Mouse object.
     */
    public Mouse(float x, float y, World<IEntity> world) {
        this(x, y, Faction.MONSTER, world);
    }

    /**
     * Constructor with custom faction tag
     * @param faction custom faction tag
     */
    public Mouse(float x, float y, Faction faction, World<IEntity> world) {
        super(x, y, 16, 16, 2, 1, 1, faction, world, 1, 1);
    }

    /**
     * Moves the mouse in the direction it is facing. After a certain amount of time since changing the direction has
     * passed, the direction of the mouse is randomly changed, so it moves toward the target
     * in the x-direction or y-direction.
     * @param targetX the x-coordinate of the target
     * @param targetY the y-coordinate of the target
     * @return the collisions that occurred when moving
     */
    @Override
    public Collisions moveTowardTarget(float targetX, float targetY) {
        float xDistance = targetX - getX();
        float yDistance = targetY - getY();
        float timeSinceDirectionChanged = Time.getInstance().getTicks() - timeWhenDirectionChanged;

        if (timeSinceDirectionChanged > 36) {
            timeWhenDirectionChanged = Time.getInstance().getTicks();
            if (Math.random() < 0.5) {
                if ((xDistance > 0)) {
                    return move(Direction.RIGHT);
                } else {
                    return move(Direction.LEFT);
                }
            }
            else {
                if ((yDistance > 0)) {
                    return move(Direction.UP);
                } else {
                    return move(Direction.DOWN);
                }
            }
        }
        else {
            return moveForward();
        }
    }
}
