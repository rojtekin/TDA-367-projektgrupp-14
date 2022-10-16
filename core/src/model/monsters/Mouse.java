package model.monsters;

import model.Entity;
import com.badlogic.gdx.Gdx;
import com.dongbat.jbump.World;

public class Mouse extends Monster {
    private float timeSinceDirectionChanged = 0;

    /**
     * Uses the default constructor of its superclass with default values
     */
    public Mouse(float x, float y, float speed, float health, float damage, World<Entity> world) {
        super(x, y, 16, 16, speed, health, damage, DEFAULTFACTION, world);
    }

    /**
     * Constructor with custom faction tag
     * @param faction custom faction tag
     */
    public Mouse(float x, float y, float speed, float health, float damage, String faction, World<Entity> world) {
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
                    this.moveRight();
                } else {
                    this.moveLeft();
                }
            }
            else {
                if ((yDistance > 0)) {
                    this.moveUp();
                } else {
                    this.moveDown();
                }
            }
            timeSinceDirectionChanged = 0;
        }
        else {
            this.moveForward();
        }
    }
}
