package model;

import com.dongbat.jbump.World;

public abstract class PlayerCharacterAdapter extends LivingEntity implements IPlayerCharacter {

    private int experience;
    private int level;
    private float AbilityCoolDownMultiplier;
    private float AbilityPower;
    private Direction direction;
    private static final int EXPERIENCE_THRESHOLD = 100;

    public PlayerCharacterAdapter(float x, float y, float height, float width, float speed, float maxHealth , float damage, Faction faction, World<IEntity> world) {
        super(x, y, height, width, speed, maxHealth, damage, faction, world);
        this.experience = 0;
        this.level = 0;
        this.AbilityCoolDownMultiplier = 1;
        this.direction = Direction.DOWN;
    }

    @Override
    public float getMaxHealth() {
        return super.getMaxHealth();
    }

    @Override
    public float getSpeed(){
        return super.getSpeed();
    }

    @Override
    public void increaseCurrentHealth(float amount){
        if (getCurrentHealth()+amount < getMaxHealth()){
        setCurrentHealth(getCurrentHealth()+amount);}
        else {
            setCurrentHealth(getMaxHealth());
        }
    }

    public int getExperience() {
        return experience;
    }
    private void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }
    private void setLevel(int level) {
        this.level = level;
    }

    public void increaseLevel(){ setLevel(getLevel()+1); }
    public void decreaseLevel(){ setLevel(getLevel()-1); }

    public void increaseMaxHealth() {
        setMaxHealth(getMaxHealth() + 1);
    }
    public void decreaseMaxHealth() {
        if (getMaxHealth() > 1)
            setMaxHealth(getMaxHealth() - 1);
    }

    public void increaseSpeed() {
        setSpeed(getSpeed()+1);
    }
    public void decreaseSpeed() {
        if (getSpeed() > 1)
            setSpeed(getSpeed()-1);
    }

    public void increaseDamage() {
        setDamage(getDamage()+1);
    }
    public void decreaseDamage() {
        if (getDamage() > 1)
            setDamage(getDamage()-1);
    }

    public void increasedAbilityCoolDownMultiplier() {
        setAbilityCoolDownMultiplier(getAbilityCoolDownMultiplier()+ (float) 0.05);
    }
    public void decreasedAbilityCoolDownMultiplier() {
        if (getAbilityCoolDownMultiplier() > 0.05)
            setAbilityCoolDownMultiplier(getAbilityCoolDownMultiplier()- (float) 0.05);
    }

    public void increaseAbilityPower() {
        setAbilityPower(getAbilityPower()+1);
    }
    public void decreaseAbilityPower() {
        if (getAbilityPower() > 1)
            setAbilityPower(getAbilityPower()-1);
    }

    public void gainExperience(int experience) {
        setExperience(getExperience() + experience);
    }
    public void reduceExperience() { setExperience(getExperience()-100); }

    public float getAbilityPower() { return AbilityPower; }
    private void setAbilityPower(float abilityPower) {
        AbilityPower = abilityPower;
    }

    public float getAbilityCoolDownMultiplier() {
        return AbilityCoolDownMultiplier;
    }
    private void setAbilityCoolDownMultiplier(float abilityCoolDownMultiplier) {
        AbilityCoolDownMultiplier = abilityCoolDownMultiplier;
    }
    public boolean levelUpCheck(){
        return experience >= EXPERIENCE_THRESHOLD;
    }
}
