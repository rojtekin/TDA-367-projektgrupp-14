package model.playerperks;

import com.dongbat.jbump.World;
import model.IEntity;
import model.IPlayerCharacter;
import model.PlayerCharacter;

public class Tank extends PlayerCharacter implements IPlayerCharacter {
    IPlayerCharacter playerCharacter;

    public Tank(IPlayerCharacter playerCharacter, World<IEntity> world) {
        super(playerCharacter.getX(), playerCharacter.getY(), world);
        this.playerCharacter = playerCharacter;
        getPerkList().add(this.getClass().getSimpleName());
    }

    @Override
    public float getDamage() {
        return playerCharacter.getDamage() * (float) 0.75;
    }

    @Override
    public float getCurrentHealth() {
        return playerCharacter.getMaxHealth() * (float) 1.5;
    }


    @Override
    public float getMaxHealth(){
        return playerCharacter.getMaxHealth()*0.75f;
    }
}