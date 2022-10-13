package model;

import com.dongbat.jbump.World;


/**
 * A class for static objects placed on the map. Used to assign collision to map objects.
 */
public class PlacedMapEntity extends Entity {
    public PlacedMapEntity(float x, float y, float height, float width, World<IEntity> world) {
        super(x, y, height, width, 0, 1000000,0, world);
    }

    @Override
    public void move(Direction direction, Float speed) {
    }
}
