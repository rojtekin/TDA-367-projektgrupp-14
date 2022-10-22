package model;

import com.dongbat.jbump.Collisions;

public interface IControllable {

    Collisions move(Direction direction, float speed);

    void weaponAttack(int rotationStart, int rotationFinish, int animationpart);
}
