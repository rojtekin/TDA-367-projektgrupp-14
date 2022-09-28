package Model.Enemies;

import Model.Entity;
import Model.IModel;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class Mouse extends Enemy {

    public Mouse(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health, damage, world);
        setImagePath("Enemies/mouse-idle-down.png");
        addCollision();
    }
}
