package model;

import model.enemies.Enemy;

public class enemyDamageVisitor implements Visitor {


    @Override
    public void doDamage(PlayerCharacter player, float damage) {
        player.takeDamage(damage);
    }

    @Override
    public void doDamage(Enemy enemy, float damage) {
    }

    @Override
    public void doDamage(Entity entity, float damage) {

    }

}
