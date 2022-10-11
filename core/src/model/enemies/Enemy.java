package model.enemies;

import model.LivingEntity;
import model.Entity;
import com.dongbat.jbump.World;

public abstract class Enemy extends LivingEntity {
    private double damage;

    public Enemy(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health, world);
        this.damage = damage;
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    abstract public void moveTowardPlayer(float playerX, float playerY);
}
