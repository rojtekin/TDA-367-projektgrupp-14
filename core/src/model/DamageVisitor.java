package model;

import model.enemies.Enemy;

public class DamageVisitor implements IDamageVisitor {


    @Override
    public void doDamage(PlayerCharacter target, float damage, String faction) {
        if (target.getFaction() != faction) {
            target.takeDamage(damage);
        }
    }

    @Override
    public void doDamage(Enemy target, float damage, String faction) {
        if (target.getFaction() != faction) {
            target.takeDamage(damage);
        }
    }

    @Override
    public void doDamage(Entity target, float damage, String faction) {
    }

}
