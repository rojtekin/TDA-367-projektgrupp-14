package model;

import model.monsters.Monster;

/**
 * Concrete visitor for doing damage in general. Faction tag system prevents
 * friendly entities from attacking eachother but also allows entities to "convert"
 * to another facton. For example an ability could allow a cyclops to fight for the player.
 */
public class DamageVisitor implements IDamageVisitor {


    /**
     * Uses double dispatch to determine the class of the target.
     * @param target The target of the damage
     * @param damage The amount of damage to be inflicted
     * @param faction The attacker's faction.
     *                If the attacker is of the
     *                same faction no damage will be done.
     */
    @Override
    public void doDamage(PlayerCharacter target, float damage, String faction) {
        if (target.getFaction() != faction) {
            target.takeDamage(damage);
        }
    }

    @Override
    public void doDamage(Monster target, float damage, String faction) {
        if (target.getFaction() != faction) {
            target.takeDamage(damage);
        }
    }

    /**
     *  If the entity that is being attacked is not of a type that can take damage then
     *  nothing will be done.
     */
    @Override
    public void doDamage(Entity target, float damage, String faction) {
    }

}
