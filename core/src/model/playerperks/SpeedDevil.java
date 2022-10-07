package model.playerperks;

import model.Direction;
import model.IPlayerCharacter;
import model.PlayerCharacter;


public class SpeedDevil implements IPlayerCharacter{
    private IPlayerCharacter playerCharacter;
    public SpeedDevil(IPlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    @Override
    public float getSpeed(){
        return playerCharacter.getSpeed()* (float)1.5;
    }

    @Override
    public float getDamage() {
        return playerCharacter.getDamage();
    }

    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public int getExperience() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public float getCurrentHealth() {
        return playerCharacter.getMaxHealth()* (float) 0.75;
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
    public float getX() {
        return playerCharacter.getX();
    }

    @Override
    public float getY() {
        return playerCharacter.getY();
    }

    @Override
    public void moveLeft() {
        playerCharacter.moveLeft();
    }

    @Override
    public void moveRight() {
        playerCharacter.moveRight();
    }

    @Override
    public void moveUp() {
        playerCharacter.moveUp();
    }

    @Override
    public void moveDown() {
        playerCharacter.moveDown();
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
