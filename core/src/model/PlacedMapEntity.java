package model;

import com.dongbat.jbump.World;

/**
 * A class for static objects placed on the map. Used to assign collision to map objects.
 */
public class PlacedMapEntity extends Entity {
    public PlacedMapEntity(float x, float y, float height, float width, float damage, World<IEntity> world) {
        super(x, y, height, width, damage, world);
    }

    @Override
    public void beAttacked(float damage, Faction faction) {
    }
}
