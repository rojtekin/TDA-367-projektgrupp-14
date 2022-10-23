package model;

import view.ISoundSubscriber;

public interface IModelPublisher {

    void addSubscriber(ISoundSubscriber subscriber);

    void removeSubscriber(ISoundSubscriber subscriber);

    void notifyPlayerDeath();

    void notifyMonsterAttack();
}
