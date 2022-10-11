package model;

import model.enemies.Enemy;

public interface Visitor {

    void doDamage(Enemy enemy, float damage);

    void doDamage(PlayerCharacter player, float damage);

    void doDamage(Entity entity, float damage);
}
