package model.monsters;

import model.LivingEntity;
import model.Entity;
import com.dongbat.jbump.World;
import model.IDamageVisitor;

public abstract class Monster extends LivingEntity {
    //Bad practice? only works if static
    private static final String DEFAULTFACTION = "hostile";
    private double damage;

    public Monster(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health, 1, DEFAULTFACTION, world);
        this.damage = damage;
    }

    /**
     * Alternative constructor that allows custom factions
     * @param faction Alternative faction
     */
    public Monster(float x, float y, float height, float width, float speed, float health, float damage, String faction, World<Entity> world) {
        super(x, y, height, width, speed, health, 1, faction, world);
        this.damage = damage;
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    abstract public void moveTowardPlayer(float playerX, float playerY);

    @Override
    public void beAttacked(IDamageVisitor v, float damage, String faction) {
        v.doDamage(this, damage, faction);
    }
}
