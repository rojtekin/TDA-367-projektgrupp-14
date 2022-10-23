package model;

import view.ISoundSubscriber;

/**
 * Allows the model to publish to any subscriber that changes
 * in the model have occured.
 */
public interface IModelPublisher {

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
     * Notify the subscribers that the player has died
     */
    void notifyPlayerDeath();

    /**
     * Notify the subscribers that a monster has made an
     * attack
     */
    void notifyMonsterAttack();
}
