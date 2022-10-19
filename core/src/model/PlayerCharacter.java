package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends PlayerCharacterAdapter implements IControllable, IPlayerCharacter {

    private boolean swinging;
    public Sword getWeapon() {
        return weapon;
    }

    private Sword weapon;
    public boolean isSwinging() {
        return swinging;
    }

    public void setSwinging(boolean swinging) {
        this.swinging = swinging;
    }



    /**
     * Default constructor for a default sized player of the player faction
     * @param spawnX spawn location along x axis
     * @param spawnY spawn location along y axis
     * @param world world that the character moves in
     */
    public PlayerCharacter(float spawnX, float spawnY, World<IEntity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, Faction.PLAYER, world);
        weapon = new Sword(world);
    }

    //weaponswing unsure if i should split since i might combine animation and movement
    //animationspart is used since i want a recursive animation. Example every time i call
    //the function animationpart is reduced by one, use this to determine the swords current rotation
    //and next time it will rotate a bit more
    public void weaponAttack(int rotationStart, int rotationFinish, int animationpart){
        weapon.weaponSwing(rotationStart,rotationFinish,animationpart, this);
    }


    /**
     * Alternative constructor that allows a player to be of another faction.
     * Potential use in multiplayer
     * @param faction that the player belongs to
     */
    public PlayerCharacter(float spawnX, float spawnY, Faction faction, World<IEntity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, faction, world);
        weapon = new Sword(world);
    }
}
