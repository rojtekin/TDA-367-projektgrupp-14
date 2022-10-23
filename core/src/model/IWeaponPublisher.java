package model;

import view.ISoundSubscriber;

/**
 * Allows the weapon to publish to any subscriber that a swordswing has occurred
 */
public interface IWeaponPublisher {

    /**
     * Adds a soundSubscriber to a list of subscribers
     * @param subscriber to be added to the list of subscribers
     */
    void addSubscriber(ISoundSubscriber subscriber);

    /**
     * Removes a soundSubscriber from a list of subscribers
     * @param subscriber to be removed from the list of subscribers
     */
    void removeSubscriber(ISoundSubscriber subscriber);

    /**
     * Notifies the subscriber that a weaponswing has occured
     */
    void notifyWeaponswing();
}
