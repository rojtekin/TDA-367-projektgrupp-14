package Model;

import com.dongbat.jbump.World;

public class StaticObjectEntity extends Entity {
    public StaticObjectEntity(float x, float y, float height, int width, World<Entity> world) {
        super(x, y, height, width, 0, 1000000, world);
    }
}
