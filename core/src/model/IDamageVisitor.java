package model;

import model.monsters.Monster;

/**
 * Interface for visitor pattern that inflicts damage. Determines damage done
 * by double dispatch and a "tag" system using strings. Should allow for
 * future extensibility by implementing new visitors that have different damage behaviour.
 */
public interface IDamageVisitor {

    void doDamage(Monster target, float damage, String faction);

    void doDamage(PlayerCharacter target, float damage, String faction);

    void doDamage(Entity target, float damage, String faction);
}
