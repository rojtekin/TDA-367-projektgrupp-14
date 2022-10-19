package model;

public interface IControllable {

    void move(Direction direction, float speed);

    void weaponAttack(int rotationStart, int rotationFinish, int animationpart);
}
