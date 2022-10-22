package model.monsters;

import com.dongbat.jbump.Collisions;
import model.Direction;
import com.dongbat.jbump.World;
import model.Faction;
import model.IEntity;

public class Cyclops extends Monster {
    private boolean stuckInXDirection = false;
    private boolean stuckInYDirection = false;

    /**
     * Creates a Cyclops object.
     */
    public Cyclops(float x, float y, World<IEntity> world) {
        this(x, y, Faction.MONSTER, world);
    }

    /**
     * Constructor with custom faction tag
     * @param faction custom faction tag
     */
    public Cyclops(float x, float y, Faction faction, World<IEntity> world) {
        super(x, y, 32, 32, 1, 10, 5, faction, world, 1, 5);
    }

    /**
     * Moves the cyclops toward the target in the x-direction or in the y-direction.
     * If the cyclops gets stuck in the x-direction, it is moved up until it is no longer stuck.
     * If it gets stuck in the y-direction, it is moved right until it is no longer stuck.
     * @param targetX the x-coordinate of the target
     * @param targetY the y-coordinate of the target
     */
    public Collisions moveTowardTarget(float targetX, float targetY) {
        float xDistance = targetX - getX();
        float yDistance = targetY - getY();
        float initialX = getX();
        float initialY = getY();
        Collisions collisions;

        if ((Math.abs(xDistance) > 16) && !stuckInYDirection) {
            if (xDistance > 0) {
                collisions = move(Direction.RIGHT, getSpeed());
            }
            else {
                collisions = move(Direction.LEFT, getSpeed());
            }
            stuckInXDirection = (getX() == initialX);
            if (stuckInXDirection) {
                collisions = move(Direction.UP, getSpeed());
            }
        }
        else if(!stuckInXDirection){
            if (yDistance > 0) {
                collisions = move(Direction.UP, getSpeed());
            }
            else {
                collisions = move(Direction.DOWN, getSpeed());
            }
            stuckInYDirection = (getY() == initialY);
            if (stuckInYDirection) {
                collisions = move(Direction.RIGHT, getSpeed());
            }
        }
        else {
            collisions = moveForward(getSpeed());
        }
        return collisions;
    }
}
