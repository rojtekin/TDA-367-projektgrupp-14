package model.playerperks;

import com.dongbat.jbump.World;
import model.IEntity;
import model.IPlayerCharacter;
import model.PlayerCharacter;


public class SpeedDevil extends PlayerCharacter implements IPlayerCharacter{
    private final IPlayerCharacter playerCharacter;
    public SpeedDevil(IPlayerCharacter playerCharacter, World<IEntity> world) {
        super(playerCharacter.getX(),playerCharacter.getY(), world);
        this.playerCharacter = playerCharacter;
        getPerkList().add(this.getClass().getSimpleName());
        setExperience(playerCharacter.getExperience());
        setLevel(playerCharacter.getLevel());
        setAbilityPower(playerCharacter.getAbilityPower());
    }

    @Override
    public float getSpeed(){
        return playerCharacter.getSpeed()* 1.5f;
    }

    @Override
    public float getCurrentHealth() {
        return playerCharacter.getMaxHealth()* 0.75f;
    }

    @Override
    public float getMaxHealth(){
        return playerCharacter.getMaxHealth()*0.75f;
    }
}
