package Model.Enemies;

import Model.Entity;
import com.dongbat.jbump.World;

public class Mouse extends Enemy {

    public Mouse(float x, float y, float speed, float health, float damage, World<Entity> world) {
        super(x, y, 16, 16, speed, health, damage, world, "Mouse");
    }

}
