package model.monsters;

import model.LivingEntity;
import model.Entity;
import com.dongbat.jbump.World;
import model.IDamageVisitor;

public abstract class Monster extends LivingEntity {
    private double damage;

    public Monster(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health, 1, "Hostile", world);
        this.damage = damage;
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    abstract public void moveTowardPlayer(float playerX, float playerY);

    @Override
    public void receiveDamage(IDamageVisitor v, float damage, String faction) {
        v.doDamage(this, damage, faction);
    }
}
