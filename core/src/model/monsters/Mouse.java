package model.monsters;

import model.*;
import com.dongbat.jbump.World;
import utility.Time;

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
     * passed, the direction of the mouse is randomly changed, so it moves toward the player character
     * in the x-direction or y-direction.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    @Override
    public void moveTowardPlayer(float playerX, float playerY) {
        float xDistance = playerX - this.getX();
        float yDistance = playerY - this.getY();
        float timeSinceDirectionChanged = Time.getInstance().getTicks() - timeWhenDirectionChanged;

        if (timeSinceDirectionChanged > 36) {
            if (Math.random() < 0.5) {
                if ((xDistance > 0)) {
                    this.move(Direction.RIGHT, getSpeed());
                } else {
                    this.move(Direction.LEFT, getSpeed());
                }
            }
            else {
                if ((yDistance > 0)) {
                    this.move(Direction.UP, getSpeed());
                } else {
                    this.move(Direction.DOWN, getSpeed());
                }
            }
            timeWhenDirectionChanged = Time.getInstance().getTicks();
        }
        else {
            this.moveForward(getSpeed());
        }
    }
}
