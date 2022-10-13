package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends Entity implements IControllable, IPlayerCharacter {
    private int experience;
    private int level;
    private float AbilityCoolDownMultiplier; // for abilities later 1 -> full cool down, 0--> everything has 0sec cool down
    private float AbilityPower;

    public PlayerCharacter(float spawnX, float spawnY, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10,1, world);
        this.experience = 0;
        this.level = 0;
        this.AbilityCoolDownMultiplier = 1;
    } //TODO fix later

    public int getExperience() {
        return experience;
    }

    protected void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    public float getAbilityPower() {
        return AbilityPower;
    }

    @Override
    public void increaseMaxHealth() {
        setMaxHealth(getMaxHealth() + 1);
    }

    @Override
    public void decreaseMaxHealth() {
        if (getMaxHealth() > 1)
        setMaxHealth(getMaxHealth() - 1);
    }

    @Override
    public void increaseSpeed() {
        setSpeed(getSpeed()+1);
    }

    @Override
    public void decreaseSpeed() {
        if (getSpeed() > 1)
        setSpeed(getSpeed()-1);
    }

    @Override
    public void increaseDamage() {
        setDamage(getDamage()+1);
    }

    @Override
    public void decreaseDamage() {
        if (getDamage() > 1)
        setDamage(getDamage()-1);
    }

    @Override
    public void increasedAbilityCoolDownMultiplier() {
        setAbilityCoolDownMultiplier(getAbilityCoolDownMultiplier()+ (float) 0.05);
    }

    @Override
    public void decreasedAbilityCoolDownMultiplier() {
        if (getAbilityCoolDownMultiplier() > 0.05)
            setAbilityCoolDownMultiplier(getAbilityCoolDownMultiplier()- (float) 0.05);
    }

    @Override
    public void increaseAbilityPower() {
        setAbilityPower(getAbilityPower()+1);
    }

    @Override
    public void decreaseAbilityPower() {
        if (getAbilityPower() > 1)
            setAbilityPower(getAbilityPower()-1);
    }

    protected void setAbilityPower(float abilityPower) {
        AbilityPower = abilityPower;
    }

    public float getAbilityCoolDownMultiplier() {
        return AbilityCoolDownMultiplier;
    }

    protected void setAbilityCoolDownMultiplier(float abilityCoolDownMultiplier) {
        AbilityCoolDownMultiplier = abilityCoolDownMultiplier;
    }

    @Override
    public void move(Direction direction, Float speed) {
        super.move(direction, speed);
    }
}
