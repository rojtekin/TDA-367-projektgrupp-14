package model;

import view.ISoundSubscriber;

public interface IMonsterSoundPublisher {

    void addSubscriber(ISoundSubscriber subscriber);

    void removeSubscriber(ISoundSubscriber subscriber);

    void notifyMonsterAttack();
}
