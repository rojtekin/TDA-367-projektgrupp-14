package Model.Enemies;

import Model.Enemy;

public class Mouse extends Enemy {

    public Mouse(int x, int y, int height, int width, int speed, double health, double damage) {
        super(x, y, height, width, speed, health, damage);
        setEntityName("Mouse");
    }
}
