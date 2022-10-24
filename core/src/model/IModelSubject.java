package model;

import view.IObserver;

/**
 * Allows the model to publish to any observers that changes
 * in the model have occurred.
 */
public interface IModelSubject {

    /**
     * Adds a IObserver to a list of observers
     * @param observer to be added to the list of observers
     */
    void addObserver(IObserver observer);

    /**
     * Removes a IObserver from a list of observers
     * @param observer to be removed from the list of observers
     */
    void removeObserver(IObserver observer);

    /**
     * Notify the observers that the player has died
     */
    void notifyPlayerDeath();

    /**
     * Notify the observers that a monster has made an
     * attack
     */
    void notifyMonsterAttack();
}
