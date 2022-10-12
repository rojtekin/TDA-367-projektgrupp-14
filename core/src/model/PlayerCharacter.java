package model;

import com.dongbat.jbump.World;

public class PlayerCharacter extends LivingEntity implements IControllable {

    public PlayerCharacter(float spawnX, float spawnY, World<Entity> world) {
        super(spawnX, spawnY, 32, 32, 5, 10, 0, "Friendly", world);
    }

    @Override
    public void beAttacked(IDamageVisitor v, float damage, String faction) {
        v.doDamage(this, damage, faction);
    }
}
