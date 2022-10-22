package model;

import com.dongbat.jbump.World;
import model.rewards.Reward;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayerCharacter extends LivingEntity implements IPlayerCharacter {

    private int experience;
    private int level;
    private float abilityCoolDownMultiplier;
    private float abilityPower;
    private static final int EXPERIENCE_THRESHOLD = 100;
    private List<Reward> perkList = new ArrayList<>();

    public AbstractPlayerCharacter(float x, float y, float height, float width, float speed, float maxHealth , float damage, Faction faction, World<IEntity> world) {
        super(x, y, height, width, speed, maxHealth, damage, faction, world);
        this.experience = 0;
        this.level = 0;
        this.abilityCoolDownMultiplier = 1;
        this.abilityPower = 1;
    }

    @Override
    public void increaseCurrentHealth(float amount){
        setCurrentHealth(Math.min(getCurrentHealth() + amount, getMaxHealth()));
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

    public void increaseLevel(){ setLevel(getLevel()+1); }

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
    public void reduceExperience() { setExperience( (getExperience() - 100) ); }

    public float getAbilityPower() { return abilityPower; }
    protected void setAbilityPower(float abilityPower) {
        this.abilityPower = abilityPower;
    }

    public float getAbilityCoolDownMultiplier() {
        return abilityCoolDownMultiplier;
    }
    private void setAbilityCoolDownMultiplier(float abilityCoolDownMultiplier) {
        this.abilityCoolDownMultiplier = abilityCoolDownMultiplier;
    }
    public boolean levelUpCheck(){
        return experience >= EXPERIENCE_THRESHOLD;
    }

    /**
     * @return returns a list of perks applied to the player
     */
    public List<Reward> getPerkList() {
        return perkList;
    }

}
