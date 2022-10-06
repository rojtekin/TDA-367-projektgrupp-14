package model.enemies;

import model.Entity;
import com.dongbat.jbump.World;

public abstract class Enemy extends Entity {
    private double damage;
    private String enemyName = ""; // Should be the same as its corresponding prefix under assets.

    public Enemy(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world, String entityName) {
        super(x, y, height, width, speed, health, world, entityName);
        this.damage = damage;
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    abstract public void moveTowardPlayer(float playerX, float playerY);
}
