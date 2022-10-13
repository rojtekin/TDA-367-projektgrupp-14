package model;

import com.dongbat.jbump.World;
//TODO fråga: Vad ska jag göra med denna?
public abstract class PlayerCharacterAdapter extends Entity implements IPlayerCharacter {

    private float height;
    private float width;
    private int experience;
    private int level;
    private float AbilityCoolDownMultiplier; // for abilities later 1 -> full cool down, 0--> everything has 0sec cool down
    private float AbilityPower;
    private boolean inMotion = false;

    public PlayerCharacterAdapter(float spawnX, float spawnY, World<IEntity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10,1, world);
        this.experience = 0;
        this.level = 0;
        this.AbilityCoolDownMultiplier = 1;
    }

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

   /* @Override
    public void setMoving(boolean moving) {
        this.inMotion = moving;
    }
*/ // why does commenting this out fix the constalty inMotion problem? TODO ask question

    protected void setAbilityCoolDownMultiplier(float abilityCoolDownMultiplier) {
        AbilityCoolDownMultiplier = abilityCoolDownMultiplier;
    }

    @Override
    public float getHeight(){
        return this.height;
    }


    @Override
    public float getWidth(){
        return this.height;
    }
}
