package model;

import view.ISoundObserver;

/**
 * Allows the model to publish to any observers that changes
 * in the model have occurred.
 */
public interface IModelSubject {

    /**
     * Adds a ISoundObserver to a list of observers
     * @param observer to be added to the list of observers
     */
    void addObserver(ISoundObserver observer);

    /**
     * Removes a ISoundObserver from a list of observers
     * @param observer to be removed from the list of observers
     */
    void removeObserver(ISoundObserver observer);

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
