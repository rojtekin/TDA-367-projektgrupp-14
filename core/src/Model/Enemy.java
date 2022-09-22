package Model;

public abstract class Enemy extends Entity{
    private double damage;
    private String enemyName = ""; // Should be the same as its corresponding prefix under assets.

    public Enemy(int x, int y, int height, int width, int speed, double health, double damage) {
        super(x, y, height, width, speed, health);
        this.damage = damage;
    }
}
