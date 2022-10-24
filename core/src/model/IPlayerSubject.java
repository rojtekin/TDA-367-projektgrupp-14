package model;

import view.IObserver;

/**
 * Allows the subject to notify all observers that a swordswing has occurred
 */
public interface IPlayerSubject extends IPlayerCharacter {

    /**
     * Adds an IObserver to a list of observers
     * @param observer to be added to the list of observers
     */
    void addObserver(IObserver observer);

    /**
     * Removes a IObserver from a list of observers
     * @param observer to be removed from the list of observers
     */
    void removeObserver(IObserver observer);

    /**
     * Notifies the observers that a weaponswing has occured
     */
    void notifyWeaponswing();
}
