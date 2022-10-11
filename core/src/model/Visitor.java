package model;

public interface Visitor {

    void doDamage(LivingEntity entity, float damage);

    void doDamage(Entity entity, float damage);
}
