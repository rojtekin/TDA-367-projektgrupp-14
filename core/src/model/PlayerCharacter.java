package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends LivingEntity implements IControllable {

    private static final String DEFAULTFACTION = "player";

    /**
     * Default constructor for a default sized player of the player faction
     * @param spawnX spawn location along x axis
     * @param spawnY spawn location along y axis
     * @param world world that the character moves in
     */
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
}
