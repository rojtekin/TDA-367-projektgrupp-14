package model;

import com.dongbat.jbump.World;
import model.rewards.LivingTrait;
import model.rewards.Tweak;

import java.util.*;


public class PlayerCharacter extends AbstractPlayerCharacter implements IControllable, IPlayerCharacter {
    private final Map<LivingTrait, ArrayList<Tweak>> tweaks = new HashMap<>();

    private boolean swinging;

    private PlayerWeapon weapon;

    public PlayerWeapon getWeapon() {
        return weapon;
    }

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
        for (LivingTrait trait : LivingTrait.values()) {
            tweaks.put(trait, new ArrayList<>());
        }
    }

    public void addTweak(Set<Tweak> tweaks) {
        for (final Tweak t : tweaks) {
            this.tweaks.get(t.getTrait()).add(t);
        }
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

    @Override
    public float getSpeed() {
        float speed = super.getSpeed();
        for (Tweak t : this.tweaks.get(LivingTrait.SPEED)) {
            speed = t.apply(speed);}

        return speed;
    }

    @Override
    public float getMaxHealth(){
        float maxHealth = super.getMaxHealth();
        for (Tweak t : this.tweaks.get(LivingTrait.HEALTH)) {
            maxHealth = t.apply(maxHealth);}

        return maxHealth;
    }

    @Override
    public float getCurrentHealth(){
        float currentHealth = super.getCurrentHealth();
        for (Tweak t : this.tweaks.get(LivingTrait.HEALTH)) {
            currentHealth = t.apply(currentHealth);}
        if (currentHealth<0){ currentHealth = 0f;}
        return currentHealth;
    }

    @Override
    public float getDamage(){
        float damage = super.getDamage();
        for (Tweak t : this.tweaks.get(LivingTrait.DAMAGE)) {
            damage = t.apply(damage);}
        return damage;
    }
}
