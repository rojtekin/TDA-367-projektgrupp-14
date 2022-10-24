package model;

import com.dongbat.jbump.World;

public class Sword extends PlayerWeapon{

    /**
     * Constructs a Sword
     */
    public Sword(World<IEntity> world){
        super(world, 1,64,12,1,0,10);

    }
}
