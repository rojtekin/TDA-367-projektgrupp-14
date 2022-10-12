package model.enemies;

import model.Entity;
import com.dongbat.jbump.World;

public abstract class Enemy extends Entity {
    private double damage;

    public Enemy(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health,damage, world);
        this.damage = damage;
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    abstract public void moveTowardPlayer(float playerX, float playerY);
}
