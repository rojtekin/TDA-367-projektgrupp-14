package Model;

public abstract class Enemy extends Entity{
    private double damage;
    private String enemyName = ""; // Should be the same as its corresponding prefix under assets.

    public Enemy(int x, int y, int height, int width, int speed, float health, float damage, String entityName) {
        super(x, y, height, width, speed, health, entityName);
        this.damage = damage;
    }
}
