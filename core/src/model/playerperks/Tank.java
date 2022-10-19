package model.playerperks;

import com.dongbat.jbump.IntPoint;
import com.dongbat.jbump.World;
import model.*;

public class Tank extends PlayerCharacter implements IPlayerCharacter {
    IPlayerCharacter playerCharacter;
    public Tank(IPlayerCharacter playerCharacter, World<IEntity> world) {
        super(playerCharacter.getX(),playerCharacter.getY(), world);
        this.playerCharacter = playerCharacter;
    }

    public float getDamage(){
        return playerCharacter.getDamage()* (float)0.75;
    }

    @Override
    public boolean isMoving() {
        return playerCharacter.isMoving();
    }

    @Override
    public Direction getDirection() {
        return playerCharacter.getDirection();
    }

    @Override
    public int getExperience() {
        return playerCharacter.getExperience();
    }

    @Override
    public int getLevel() {
        return playerCharacter.getLevel();
    }

    @Override
    public float getCurrentHealth() {
        return playerCharacter.getMaxHealth() * (float) 1.5;
    }

    @Override
    public float getSpeed() {
        return playerCharacter.getSpeed();
    }

    @Override
    public float getAbilityPower() {
        return playerCharacter.getAbilityPower();
    }

    @Override
    public void increaseMaxHealth() {
        playerCharacter.increaseMaxHealth();
    }

    @Override
    public void decreaseMaxHealth() {
        playerCharacter.decreaseMaxHealth();
    }

    @Override
    public void increaseSpeed() {
        playerCharacter.increaseSpeed();
    }

    @Override
    public void decreaseSpeed() {
        playerCharacter.decreaseSpeed();
    }

    @Override
    public void increaseDamage() {
        playerCharacter.increaseDamage();
    }

    @Override
    public void decreaseDamage() {
        playerCharacter.decreaseDamage();
    }

    @Override
    public void increasedAbilityCoolDownMultiplier() {
        playerCharacter.increasedAbilityCoolDownMultiplier();
    }

    @Override
    public void decreasedAbilityCoolDownMultiplier() {
        playerCharacter.decreasedAbilityCoolDownMultiplier();
    }

    @Override
    public void increaseAbilityPower() {
        playerCharacter.increaseAbilityPower();
    }

    @Override
    public void decreaseAbilityPower() {
        playerCharacter.decreaseAbilityPower();
    }

    @Override
    public void gainExperience(int experience) {
        playerCharacter.gainExperience(experience);
    }

    @Override
    public void reduceExperience() {
        playerCharacter.reduceExperience();
    }

    @Override
    public float getAbilityCoolDownMultiplier() {
        return playerCharacter.getAbilityCoolDownMultiplier();
    }

    @Override
    public float getMaxHealth() {
        return playerCharacter.getMaxHealth();
    }

    @Override
    public void setMoving(boolean moving) {
        playerCharacter.setMoving(moving);
    }

    @Override
    public void pushBack(IntPoint normal) {
        playerCharacter.pushBack(normal);
    }

    @Override
    public float getX() {
        return playerCharacter.getX();
    }

    @Override
    public float getY() {
        return playerCharacter.getY();
    }

    @Override
    public void move(Direction direction, float speed) {
        playerCharacter.move(direction, getSpeed());
    }

    @Override
    public float getHeight() {
        return playerCharacter.getHeight();
    }

    @Override
    public float getWidth() {
        return playerCharacter.getWidth();
    }
}