package Model.Enemies;

import Model.Entity;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class Mouse extends Enemy {
    private Item<Entity> collisionbox;
    private World<Entity> world;

    public Mouse(int x, int y, int height, int width, int speed, float health, float damage) {
        super(x, y, height, width, speed, health, damage);
        setImagePath("Enemies/mouse-idle-down.png");
    }

    public void setWorld (World<Entity> world) {
        this.world = world;
        addCollision();
    }

    public void addCollision() {
        collisionbox = world.add(new Item<Entity>(this), getX(), getY(), getWidth(), getHeight());
    }

}
