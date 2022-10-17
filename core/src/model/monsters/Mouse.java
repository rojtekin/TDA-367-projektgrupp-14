package model.monsters;

import model.Direction;
import model.Entity;
import com.badlogic.gdx.Gdx;
import com.dongbat.jbump.World;
import model.Faction;
import model.IEntity;

public class Mouse extends Monster {
    private float timeSinceDirectionChanged = 0;

    /**
     * Uses the default constructor of its superclass with default values
     */
    public Mouse(float x, float y, float speed, float health, float damage, World<IEntity> world) {
        super(x, y, 16, 16, speed, health, damage, Faction.MONSTER, world);
    }

    /**
     * Constructor with custom faction tag
     * @param faction custom faction tag
     */
    public Mouse(float x, float y, float speed, float health, float damage, Faction faction, World<IEntity> world) {
        super(x, y, 16, 16, speed, health, damage, faction, world);
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
        timeSinceDirectionChanged += Gdx.graphics.getDeltaTime();

        if (timeSinceDirectionChanged > 0.6) {
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
            timeSinceDirectionChanged = 0;
        }
        else {
            this.moveForward(getSpeed());
        }
    }
}
