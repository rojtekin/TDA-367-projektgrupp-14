package Model.Enemies;

public class Mouse extends Enemy {

    public Mouse(int x, int y, int height, int width, int speed, float health, float damage) {
        super(x, y, height, width, speed, health, damage);
        setImagePath("Enemies/mouse-idle-down.png");
    }
}
