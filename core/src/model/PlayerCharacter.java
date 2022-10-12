package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends LivingEntity implements IControllable {

    private static final String DEFAULTFACTION = "player";

    public PlayerCharacter(float spawnX, float spawnY, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, DEFAULTFACTION, world);
    }

    /**
     * Alternative constructor that allows a player to be of another faction.
     * Potential use in multiplayer
     * @param faction that the player belongs to
     */
    public PlayerCharacter(float spawnX, float spawnY, String faction, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, faction, world);
    }

    @Override
    public void beAttacked(IDamageVisitor v, float damage, String faction) {
        v.doDamage(this, damage, faction);
    }
}
