package model.playerperks;

import com.dongbat.jbump.IntPoint;
import com.dongbat.jbump.World;
import model.Direction;
import model.IEntity;
import model.IPlayerCharacter;
import model.PlayerCharacter;

public class GlassCannon extends PlayerCharacter implements IPlayerCharacter {
    IPlayerCharacter playerCharacter;
    public GlassCannon(IPlayerCharacter playerCharacter, World<IEntity> world) {
        super(playerCharacter.getX(),playerCharacter.getY(), world);
        this.playerCharacter = playerCharacter;
    }
    @Override
    public float getDamage(){
        return playerCharacter.getSpeed()* (float)1.5;
    }


    @Override
    public float getMaxHealth(){
        return playerCharacter.getMaxHealth()*0.75f;
    }

    @Override
    public float getCurrentHealth() {
        return playerCharacter.getMaxHealth()* (float) 0.75;
    }

}