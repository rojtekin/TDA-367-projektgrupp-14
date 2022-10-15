package model.enemies;

import model.Entity;
import com.dongbat.jbump.World;
import model.IEntity;

public abstract class Enemy extends Entity {

    public Enemy(float x, float y, float height, float width, float speed, float health, float damage, World<IEntity> world) {
        super(x, y, height, width, speed, health,damage, world);
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    abstract public void moveTowardPlayer(float playerX, float playerY);
}
