package model;

public interface IControllable {

    float getX();

    float getY();

    void moveLeft();

    void moveRight();

    void moveUp();

    void moveDown();

    void weaponAttack(int rotationStart, int rotationFinish, int animationpart);
}
