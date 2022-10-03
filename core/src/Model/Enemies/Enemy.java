package Model.Enemies;

import Model.Entity;
import com.dongbat.jbump.World;

public abstract class Enemy extends Entity {
    private double damage;
    private String enemyName = ""; // Should be the same as its corresponding prefix under assets.

    public Enemy(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health, world);
        this.damage = damage;
    }
}
