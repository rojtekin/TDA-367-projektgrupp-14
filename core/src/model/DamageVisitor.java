package model;

public class DamageVisitor implements Visitor {


    @Override
    public void doDamage(LivingEntity entity, float damage) {
        entity.takeDamage(damage);
    }

    @Override
    public void doDamage(Entity entity, float damage) {

    }
}
