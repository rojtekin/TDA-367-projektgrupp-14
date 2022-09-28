package Model;

import com.dongbat.jbump.World;

public class StaticObjectEntity extends Entity {
    public StaticObjectEntity(int x, int y, int height, int width, World<Entity> world) {
        super(x, y, height, width, 0, 1000000, world);
    }
}
