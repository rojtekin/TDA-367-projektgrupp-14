package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends LivingEntity implements IControllable {

    private Sword weapon;
    //TODO add a weapon in constructor
    private static final String DEFAULTFACTION = "player";

    /**
     * Default constructor for a default sized player of the player faction
     * @param spawnX spawn location along x axis
     * @param spawnY spawn location along y axis
     * @param world world that the character moves in
     */
    public PlayerCharacter(float spawnX, float spawnY, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, DEFAULTFACTION, world);
        weapon = new Sword(world);
    }

    //weaponswing unsure if i should split since i might combine animation and movement
    //animationspart is used since i want a recursive animation. Example every time i call
    //the function animationpart is reduced by one, use this to determine the swords current rotation
    //and next time it will rotate a bit more
    public void weaponAttack(int rotationStart, int rotationFinish, int animationpart){
        weapon.weaponSwing(rotationStart,rotationFinish,animationpart, this);
    }

    public void locateHit(){

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
