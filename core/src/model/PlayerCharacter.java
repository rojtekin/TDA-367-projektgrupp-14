package model;

import com.dongbat.jbump.World;
import model.rewards.LivingTrait;
import model.rewards.Reward;
import model.rewards.Tweak;

import java.util.*;

/**
 * A representation of a player that additionally to its superclasses can have/swing a sword and have perks
 */

public class PlayerCharacter extends LivingEntity implements IPlayerCharacter {
    private final Map<LivingTrait, ArrayList<Tweak>> tweaks = new HashMap<>();
    private int experience;
    private int level;
    private static final int EXPERIENCE_THRESHOLD = 100;
    private final List<Reward> perkList = new ArrayList<>();
    private boolean swinging;
    private PlayerWeapon weapon;

    /**
     * Default constructor for a default sized player of the player faction
     * @param spawnX spawn location along x-axis
     * @param spawnY spawn location along y-axis
     * @param world world that the character moves in
     */
    public PlayerCharacter(float spawnX, float spawnY, World<IEntity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, Faction.PLAYER, world);
        weapon = new Sword(world);
        this.experience = 0;
        this.level = 1;
        for (LivingTrait trait : LivingTrait.values()) {
            tweaks.put(trait, new ArrayList<>());
        }
    }

    /**
     * ads a perk to the player
     * @param tweaks adds a tweak to the playerCharacter used by rewardSystem
     */
    public void addTweak(Set<Tweak> tweaks) {
        for (final Tweak t : tweaks) {
            this.tweaks.get(t.getTrait()).add(t);
        }
    }

    //weaponswing unsure if i should split since i might combine animation and movement
    //animationspart is used since i want a recursive animation. Example every time i call
    //the function animationpart is reduced by one, use this to determine the swords current rotation
    //and next time it will rotate a bit more

    /**
     * Calls upon its weapon to do a weaponSwing
     * @param rotationStart startangle of the swing
     * @param rotationFinish endangle of the swing
     * @param animationpart used to start a swing midway, if it exceeds the weapons weaponRotations it will not swing at all
     */
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
        this.experience = 0;
        this.level = 1;
        for (LivingTrait trait : LivingTrait.values()) {
            tweaks.put(trait, new ArrayList<>());
        }
    }

    @Override
    public void weaponAttack(int rotationStart, int rotationFinish){
        weapon.weaponSwing(rotationStart,rotationFinish,0, this);
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
    public int getExperience() {
        return experience;
    }

    private void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public int getLevel() {
        return level;
    }
    private void setLevel(int level) {
        this.level = level;
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

    @Override
    public void gainExperience(int experience) {
        setExperience(getExperience() + experience);
    }

    @Override
    public void reduceExperience() { setExperience( (getExperience() - getExperienceThreshold()) ); }

    @Override
    public void increaseCurrentHealth(float amount){
        setCurrentHealth(Math.min(getCurrentHealth() + amount, getMaxHealth()));
    }

    @Override
    public int getExperienceThreshold(){
        return EXPERIENCE_THRESHOLD;
    }

    @Override
    public PlayerWeapon getWeapon() {
        return weapon;
    }

    @Override
    public boolean levelUpCheck(){
        return experience >= EXPERIENCE_THRESHOLD;
    }

    @Override
    public void increaseLevel(){ setLevel(getLevel()+1); }

    /**
     * @return returns a list of perks applied to the player
     */
    @Override
    public List<Reward> getPerkList() {
        return perkList;
    }

    @Override
    public boolean isSwinging() {
        return swinging;
    }

    @Override
    public void setSwinging(boolean swinging) {
        this.swinging = swinging;
    }
}
