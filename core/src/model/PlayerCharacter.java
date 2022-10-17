package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends PlayerCharacterAdapter implements IControllable, IPlayerCharacter {

    /**
     * Default constructor for a default sized player of the player faction
     * @param spawnX spawn location along x axis
     * @param spawnY spawn location along y axis
     * @param world world that the character moves in
     */
    public PlayerCharacter(float spawnX, float spawnY, World<IEntity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, Faction.PLAYER, world);
    }

    /**
     * Alternative constructor that allows a player to be of another faction.
     * Potential use in multiplayer
     * @param faction that the player belongs to
     */
    public PlayerCharacter(float spawnX, float spawnY, Faction faction, World<IEntity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, faction, world);
    }
}
