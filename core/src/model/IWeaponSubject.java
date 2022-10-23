package model;

import view.ISoundObserver;

/**
 * Allows the weapon to notify all observers that a swordswing has occurred
 */
public interface IWeaponSubject {

    /**
     * Adds an ISoundObserver to a list of observers
     * @param observer to be added to the list of observers
     */
    void addObserver(ISoundObserver observer);

    /**
     * Removes a ISoundObserver from a list of observers
     * @param observer to be removed from the list of observers
     */
    void removeObserver(ISoundObserver observer);

    /**
     * Notifies the observers that a weaponswing has occured
     */
    void notifyWeaponswing();
}
