package model.monsters;

import model.LivingEntity;
import model.Entity;
import com.dongbat.jbump.World;

public abstract class Monster extends LivingEntity {
    //Bad practice? only works if static
    public static final String DEFAULTFACTION = "monsters";
    private double damage;

    /**
     * Default constructor for all monsters
     * @param x x spawn coordinate
     * @param y y spawn coordinate
     * @param height height of the collisionbox
     * @param width width of the collisionbox
     * @param speed The speed at which the monster moves
     * @param health The health of the monster
     * @param damage sets the value for damage to be caused when attacking.
     *               Damage done by collisions is a tenth of this value
     * @param faction The faction that this character belongs to and won't cause damage
     *                towards
     * @param world The world that the monster will move and collide in
     */
    public Monster(float x, float y, float height, float width, float speed, float health, float damage, String faction, World<Entity> world) {
        super(x, y, height, width, speed, health, damage/10, faction, world);
        this.damage = damage;
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    abstract public void moveTowardPlayer(float playerX, float playerY);
}
