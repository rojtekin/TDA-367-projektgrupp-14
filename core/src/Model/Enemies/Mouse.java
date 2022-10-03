package Model.Enemies;

import Model.Entity;
import com.dongbat.jbump.World;

public class Mouse extends Enemy {

    public Mouse(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health, damage, world, "Mouse");
    }

}
