package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends Entity implements IControllable {

    private PlayerWeapon weapon;
    //TODO add a weapon in constructor
    public PlayerCharacter(float spawnX, float spawnY, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, world);
        setEntityName("BlueSamurai");
        weapon = new PlayerWeapon(10,1,10, 5);
    }

    //weaponswing unsure if i should split since i might combine animation and movement
    //animationspart is used since i want a recursive animation. Example every time i call
    //the function animationpart is reduced by one, use this to determine the swords current rotation
    //and next time it will rotate a bit more
    public void weaponAttack(int rotationStart, int rotationFinish, int animationpart){
        weapon.weaponSwing(rotationStart,rotationFinish,animationpart);
    }

    public void locateHit(){

    }
}
