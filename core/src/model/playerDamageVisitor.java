package model;

import model.enemies.Enemy;

public class playerDamageVisitor implements Visitor {


    @Override
    public void doDamage(PlayerCharacter player, float damage) {
    }

    @Override
    public void doDamage(Enemy enemy, float damage) {
        enemy.takeDamage(damage);
    }

    @Override
    public void doDamage(Entity entity, float damage) {

    }
}
