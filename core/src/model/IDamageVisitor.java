package model;

import model.enemies.Enemy;

public interface IDamageVisitor {

    void doDamage(Enemy target, float damage, String faction);

    void doDamage(PlayerCharacter target, float damage, String faction);

    void doDamage(Entity target, float damage, String faction);
}
